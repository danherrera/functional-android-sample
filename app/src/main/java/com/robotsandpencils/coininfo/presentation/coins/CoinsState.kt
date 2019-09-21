package com.robotsandpencils.coininfo.presentation.coins

import arrow.core.Option
import com.robotsandpencils.coininfo.entities.Coin

data class CoinsState(
    val isLoading: Boolean = false,
    val coins: List<Coin> = emptyList(),
    val error: Option<Throwable> = Option.empty()
)