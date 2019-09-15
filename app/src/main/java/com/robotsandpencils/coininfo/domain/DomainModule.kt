package com.robotsandpencils.coininfo.domain

import com.robotsandpencils.coininfo.domain.allcoins.GetAllCoinsUseCase
import com.robotsandpencils.coininfo.domain.allcoins.GetAllCoinsUseCaseImpl
import com.robotsandpencils.coininfo.domain.market.GetMarketsForCoinUseCase
import com.robotsandpencils.coininfo.domain.market.GetMarketsForCoinUseCaseImpl
import org.koin.dsl.module

val domainModule = module {
    factory<GetAllCoinsUseCase> { GetAllCoinsUseCaseImpl(get()) }
    factory<GetMarketsForCoinUseCase> { GetMarketsForCoinUseCaseImpl(get()) }
}