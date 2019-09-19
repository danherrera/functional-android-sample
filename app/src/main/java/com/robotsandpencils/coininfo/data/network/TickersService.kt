package com.robotsandpencils.coininfo.data.network

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface TickersService {

    @GET("tickers")
    fun getTickers(): Call<TickersDto>

    @GET("coin/markets/")
    fun getCoinMarkets(@Query("id") coinId: String): Call<List<CoinMarketDto>>
}