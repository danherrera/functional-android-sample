package com.robotsandpencils.coininfo.presentation.coindetails

import arrow.core.Option
import com.robotsandpencils.coininfo.entities.Market

data class CoinDetailsState(
    val isLoading: Boolean = false,
    val markets: List<Market> = emptyList(),
    val error: Option<Throwable> = Option.empty()
)