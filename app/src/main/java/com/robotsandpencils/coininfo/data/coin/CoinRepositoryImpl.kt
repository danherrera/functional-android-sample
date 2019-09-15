package com.robotsandpencils.coininfo.data.coin

import com.robotsandpencils.coininfo.domain.repository.CoinRepository
import com.robotsandpencils.coininfo.entities.Coin

class CoinRepositoryImpl(
    private val coinsService: CoinsService,
    private val tickerItemMapper: TickerItemMapper
) : CoinRepository {

    override suspend fun getAllCoins(): List<Coin> {
        return coinsService.getCoins().data
            .map(tickerItemMapper::map)
    }
}