package com.robotsandpencils.coininfo.data.coin

import retrofit2.http.GET

interface CoinsService {

    @GET("tickers")
    suspend fun getCoins(): TickersDto

}