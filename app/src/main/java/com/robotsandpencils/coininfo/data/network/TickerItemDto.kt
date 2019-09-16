package com.robotsandpencils.coininfo.data.network

import kotlinx.serialization.Serializable

@Serializable
data class TickerItemDto(
    val id: String,
    val symbol: String,
    val name: String,
    val price_usd: String
)