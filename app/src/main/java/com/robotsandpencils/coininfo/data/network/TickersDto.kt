package com.robotsandpencils.coininfo.data.network

import kotlinx.serialization.Serializable

@Serializable
data class TickersDto(
    val data: List<TickerItemDto>
)