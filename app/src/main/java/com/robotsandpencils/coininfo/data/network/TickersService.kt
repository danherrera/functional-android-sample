package com.robotsandpencils.coininfo.data.network

import retrofit2.http.GET
import retrofit2.http.Query

interface TickersService {

    @GET("tickers")
    suspend fun getTickers(): TickersDto

    @GET("coin/markets/")
    suspend fun getCoinMarkets(@Query("id") coinId: String): List<CoinMarketDto>
}