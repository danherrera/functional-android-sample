package com.robotsandpencils.coininfo.data

import arrow.Kind
import com.robotsandpencils.coininfo.data.network.NetworkOperations
import com.robotsandpencils.coininfo.entities.Coin
import com.robotsandpencils.coininfo.entities.Market

interface RequestOperations<F> : NetworkOperations<F>, DomainMapper<F> {

    fun getAllCoins(): Kind<F, List<Coin>> =
        requestTickers().toCoinsFromNetwork()

    fun getMarketsForCoin(coinId: String): Kind<F, List<Market>> =
        requestMarketsForCoin(coinId).toMarketsFromNetwork()
}
