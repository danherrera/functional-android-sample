package com.robotsandpencils.coininfo.data.coin

import retrofit2.http.GET

interface TickersService {

    @GET("tickers")
    suspend fun getTickers(): TickersDto

}