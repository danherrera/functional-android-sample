package com.robotsandpencils.coininfo.data.coin

import kotlinx.serialization.Serializable

@Serializable
data class TickersDto(
    val data: List<TickerItemDto>
)