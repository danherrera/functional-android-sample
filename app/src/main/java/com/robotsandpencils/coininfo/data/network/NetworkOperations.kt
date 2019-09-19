package com.robotsandpencils.coininfo.data.network

import arrow.Kind
import arrow.typeclasses.ApplicativeError

interface NetworkOperations<F> : ApplicativeError<F, Throwable> {
    val tickersService: TickersService

    fun requestTickers(): Kind<F, List<TickerItemDto>> =
        catch { tickersService.getTickers().execute().body()!!.data }

    fun requestMarketsForCoin(coinId: String): Kind<F, List<CoinMarketDto>> =
        catch { tickersService.getCoinMarkets(coinId).execute().body()!! }
}