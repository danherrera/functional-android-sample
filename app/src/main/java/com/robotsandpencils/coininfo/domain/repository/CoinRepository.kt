package com.robotsandpencils.coininfo.domain.repository

import com.robotsandpencils.coininfo.entities.Coin

interface CoinRepository {

    suspend fun getAllCoins(): List<Coin>
}