package com.robotsandpencils.coininfo

import android.app.Application
import com.robotsandpencils.coininfo.data.dataModule
import com.robotsandpencils.coininfo.domain.domainModule
import com.robotsandpencils.coininfo.presentation.presentationModules
import org.koin.core.context.startKoin
import org.koin.dsl.module

val appModule = module {

}

class CoinInfoApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(
                listOf(
                    appModule,
                    dataModule,
                    domainModule
                ) + presentationModules
            )
        }
    }
}