package com.robotsandpencils.coininfo.data.db

import androidx.room.Room
import org.koin.dsl.module

val databaseModule = module {

    single {
        Room
            .databaseBuilder(get(), CoinInfoDatabase::class.java, "coin-info")
            .build()
    }

    single { get<CoinInfoDatabase>().coinDao() }

    single { get<CoinInfoDatabase>().marketDao() }
}