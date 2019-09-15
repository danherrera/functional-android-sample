package com.robotsandpencils.coininfo.domain.allcoins

import com.robotsandpencils.coininfo.entities.Coin

interface GetAllCoinsUseCase {

    suspend fun getAllCoins(): List<Coin>
}