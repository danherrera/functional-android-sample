package com.robotsandpencils.coininfo.data

import arrow.Kind
import com.robotsandpencils.coininfo.data.db.DatabaseOperations
import com.robotsandpencils.coininfo.data.network.NetworkOperations
import com.robotsandpencils.coininfo.entities.Coin
import com.robotsandpencils.coininfo.entities.Market

interface RepositoryOperations<F> : NetworkOperations<F>, DatabaseOperations<F>, DomainMapper<F> {

    fun getAllCoins(): Kind<F, List<Coin>> =
        queryCoins().toCoinsFromDatabase()
            .ensure({ RuntimeException("No coins in the database") }, { it.isNotEmpty() })
            .handleErrorWith {
                requestTickers().toCoinsFromNetwork()
                    .alsoSaveCoinsToDatabase()
            }

    fun getMarketsForCoin(coinId: String): Kind<F, List<Market>> =
        requestMarketsForCoin(coinId).toMarketsFromNetwork()

    fun saveCoinsToDatabase(coins: Kind<F, List<Coin>>): Kind<F, Unit> =
        coins.toCoinsForDatabase().flatMap(::saveCoins)

    private fun Kind<F, List<Coin>>.alsoSaveCoinsToDatabase(): Kind<F, List<Coin>> =
        flatMap { coins -> saveCoinsToDatabase(just(coins)).map { coins } }
}
