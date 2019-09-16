package com.robotsandpencils.coininfo.data.network

import arrow.Kind
import arrow.typeclasses.ApplicativeError
import com.robotsandpencils.coininfo.catchSuspend

interface NetworkOperations<F> : ApplicativeError<F, Throwable> {
    val tickersService: TickersService

    suspend fun requestTickers(): Kind<F, List<TickerItemDto>> =
        catchSuspend { tickersService.getTickers().data }

    suspend fun requestMarketsForCoin(coinId: String): Kind<F, List<CoinMarketDto>> =
        catchSuspend { tickersService.getCoinMarkets(coinId) }
}