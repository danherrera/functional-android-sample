package com.robotsandpencils.coininfo.presentation.coins

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val coinsModule = module {
    viewModel { CoinsViewModel(get()) }
}