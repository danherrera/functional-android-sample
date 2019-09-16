package com.robotsandpencils.coininfo.data.network

import kotlinx.serialization.Serializable

@Serializable
data class CoinMarketDto(
    val name: String,
    val base: String,
    val quote: String,
    val price_usd: Double
)