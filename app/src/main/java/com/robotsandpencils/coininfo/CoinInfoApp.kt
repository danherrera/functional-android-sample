package com.robotsandpencils.coininfo

import android.app.Application
import android.content.Context
import arrow.fx.ForIO
import arrow.fx.IO
import arrow.fx.extensions.io.monadError.monadError
import arrow.typeclasses.MonadError
import com.robotsandpencils.coininfo.data.RepositoryOperations
import com.robotsandpencils.coininfo.data.dataModules
import com.robotsandpencils.coininfo.data.db.coin.CoinDao
import com.robotsandpencils.coininfo.data.network.TickersService
import com.robotsandpencils.coininfo.presentation.presentationModules
import org.koin.core.context.startKoin
import org.koin.dsl.module

class CoinInfoApp : Application() {

    override fun onCreate() {
        super.onCreate()

        val platformModule = module {
            single<Context> { this@CoinInfoApp }

            factory<RepositoryOperations<ForIO>> {
                object : RepositoryOperations<ForIO>,
                    MonadError<ForIO, Throwable> by IO.monadError() {
                    override val tickersService: TickersService = get()
                    override val coinDao: CoinDao = get()
                }
            }
        }

        startKoin {
            modules(listOf(platformModule) + dataModules + presentationModules)
        }
    }
}