package com.robotsandpencils.coininfo.data.coin

import kotlinx.serialization.Serializable

@Serializable
data class TickersResponse(
    val data: List<TickerItemResponse>
)