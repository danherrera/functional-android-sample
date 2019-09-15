package com.robotsandpencils.coininfo.data

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.robotsandpencils.coininfo.data.coin.CoinRepositoryImpl
import com.robotsandpencils.coininfo.data.coin.CoinsService
import com.robotsandpencils.coininfo.data.coin.TickerItemMapper
import com.robotsandpencils.coininfo.data.market.CoinMarketMapper
import com.robotsandpencils.coininfo.data.market.CoinMarketRepositoryImpl
import com.robotsandpencils.coininfo.data.market.CoinMarketsService
import com.robotsandpencils.coininfo.domain.repository.CoinRepository
import com.robotsandpencils.coininfo.domain.repository.MarketRepository
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit

val dataModule = module {

    single {
        OkHttpClient()
    }
    single<Retrofit> {
        Retrofit.Builder()
            .baseUrl("https://api.coinlore.com/api/")
            .client(get())
            .addConverterFactory(Json.nonstrict.asConverterFactory("application/json".toMediaType()))
            .build()
    }

    // coin
    factory<CoinsService> { get<Retrofit>().create(CoinsService::class.java) }
    factory { TickerItemMapper() }
    single<CoinRepository> { CoinRepositoryImpl(get(), get()) }

    // market
    factory<CoinMarketsService> { get<Retrofit>().create(CoinMarketsService::class.java) }
    factory { CoinMarketMapper() }
    single<MarketRepository> { CoinMarketRepositoryImpl(get(), get()) }
}