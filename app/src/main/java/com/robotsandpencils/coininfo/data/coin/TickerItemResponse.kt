package com.robotsandpencils.coininfo.data.coin

import kotlinx.serialization.Serializable

@Serializable
data class TickerItemResponse(
    val id: String,
    val symbol: String,
    val name: String,
    val price_usd: String
)