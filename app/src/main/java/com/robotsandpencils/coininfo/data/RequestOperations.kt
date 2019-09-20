package com.robotsandpencils.coininfo.data

import arrow.Kind
import com.robotsandpencils.coininfo.data.db.DatabaseOperations
import com.robotsandpencils.coininfo.data.network.NetworkOperations
import com.robotsandpencils.coininfo.entities.Coin
import com.robotsandpencils.coininfo.entities.Market

interface RequestOperations<F> : NetworkOperations<F>, DatabaseOperations<F>, DomainMapper<F> {

    fun getAllCoins(): Kind<F, List<Coin>> =
        queryCoins().toCoinsFromDatabase()
            .ensure({ RuntimeException("No coins in the database") }, { it.isNotEmpty() })
            .handleErrorWith {
                requestTickers().toCoinsFromNetwork()
            }

    fun getMarketsForCoin(coinId: String): Kind<F, List<Market>> =
        requestMarketsForCoin(coinId).toMarketsFromNetwork()
}
