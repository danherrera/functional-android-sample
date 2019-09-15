package com.robotsandpencils.coininfo.data.market

import com.robotsandpencils.coininfo.domain.repository.MarketRepository
import com.robotsandpencils.coininfo.entities.Market

class CoinMarketRepositoryImpl(
    private val coinMarketsService: CoinMarketsService,
    private val coinMarketMapper: CoinMarketMapper
) : MarketRepository {

    override suspend fun getMarketsForCoin(coinId: String): List<Market> {
        return coinMarketsService.getCoinMarkets(coinId)
            .map(coinMarketMapper::map)
    }
}