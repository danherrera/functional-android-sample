package com.robotsandpencils.coininfo.data.coin

import com.robotsandpencils.coininfo.domain.repository.CoinRepository
import com.robotsandpencils.coininfo.entities.Coin

class CoinRepositoryImpl(
    private val coinsService: TickersService,
    private val tickerItemMapper: TickerItemMapper
) : CoinRepository {

    override suspend fun getAllCoins(): List<Coin> {
        return coinsService.getTickers().data
            .map(tickerItemMapper::map)
    }
}