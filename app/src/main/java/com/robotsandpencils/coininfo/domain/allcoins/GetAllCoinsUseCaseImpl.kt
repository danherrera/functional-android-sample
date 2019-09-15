package com.robotsandpencils.coininfo.domain.allcoins

import com.robotsandpencils.coininfo.domain.repository.CoinRepository
import com.robotsandpencils.coininfo.entities.Coin

class GetAllCoinsUseCaseImpl(
    private val coinsRepository: CoinRepository
) : GetAllCoinsUseCase {

    override suspend fun getAllCoins(): List<Coin> {
        return coinsRepository.getAllCoins()
    }
}