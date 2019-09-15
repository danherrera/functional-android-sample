package com.robotsandpencils.coininfo.domain.repository

import com.robotsandpencils.coininfo.entities.Market

interface MarketRepository {

    suspend fun getMarketsForCoin(coinId: String): List<Market>
}