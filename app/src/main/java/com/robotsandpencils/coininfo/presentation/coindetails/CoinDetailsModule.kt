package com.robotsandpencils.coininfo.presentation.coindetails

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val coinDetailsModule = module {
    viewModel { CoinDetailsViewModel(get()) }
}