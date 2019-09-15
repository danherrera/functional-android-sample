package com.robotsandpencils.coininfo.domain.market

import com.robotsandpencils.coininfo.domain.repository.MarketRepository
import com.robotsandpencils.coininfo.entities.Market

class GetMarketsForCoinUseCaseImpl(
    private val marketRepository: MarketRepository
) : GetMarketsForCoinUseCase {

    override suspend fun getMarketsForCoin(coinId: String): List<Market> {
        return marketRepository.getMarketsForCoin(coinId)
    }
}