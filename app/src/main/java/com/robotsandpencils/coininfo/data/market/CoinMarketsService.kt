package com.robotsandpencils.coininfo.data.market

import retrofit2.http.GET
import retrofit2.http.Query

interface CoinMarketsService {

    @GET("coin/markets/")
    suspend fun getCoinMarkets(@Query("id") coinId: String): List<CoinMarketDto>
}