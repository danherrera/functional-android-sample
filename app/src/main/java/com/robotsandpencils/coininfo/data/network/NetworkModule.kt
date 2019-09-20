package com.robotsandpencils.coininfo.data.network

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit

val networkModule = module {

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

    factory<TickersService> { get<Retrofit>().create(TickersService::class.java) }
}