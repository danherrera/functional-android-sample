package com.robotsandpencils.coininfo.domain.market

import com.robotsandpencils.coininfo.entities.Market

interface GetMarketsForCoinUseCase {

    suspend fun getMarketsForCoin(coinId: String): List<Market>
}