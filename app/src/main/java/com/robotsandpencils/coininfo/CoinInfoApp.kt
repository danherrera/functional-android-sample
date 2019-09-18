package com.robotsandpencils.coininfo

import android.app.Application
import arrow.core.ForTry
import arrow.core.Try
import arrow.core.extensions.`try`.monadError.monadError
import arrow.typeclasses.MonadError
import com.robotsandpencils.coininfo.data.RequestOperations
import com.robotsandpencils.coininfo.data.dataModule
import com.robotsandpencils.coininfo.data.network.TickersService
import com.robotsandpencils.coininfo.presentation.presentationModules
import org.koin.core.context.startKoin
import org.koin.dsl.module

val appModule = module {

    factory<RequestOperations<ForTry>> {
        object : RequestOperations<ForTry>, MonadError<ForTry, Throwable> by Try.monadError() {
            override val tickersService: TickersService = get()
        }
    }
}

class CoinInfoApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(
                listOf(
                    appModule,
                    dataModule
                ) + presentationModules
            )
        }
    }
}