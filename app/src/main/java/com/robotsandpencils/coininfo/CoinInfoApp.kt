package com.robotsandpencils.coininfo

import android.app.Application
import arrow.Kind
import arrow.core.Either
import arrow.core.ForTry
import arrow.core.Try
import arrow.core.fix
import com.robotsandpencils.coininfo.data.RequestOperations
import com.robotsandpencils.coininfo.data.network.TickersService
import com.robotsandpencils.coininfo.data.dataModule
import com.robotsandpencils.coininfo.presentation.presentationModules
import org.koin.core.context.startKoin
import org.koin.dsl.module

val appModule = module {

    factory<RequestOperations<ForTry>> {
        object : RequestOperations<ForTry> {
            override fun <A> just(a: A): Kind<ForTry, A> = Try.just(a)

            override fun <A> raiseError(e: Throwable): Kind<ForTry, A> = Try.raiseError(e)

            override fun <A> Kind<ForTry, A>.handleErrorWith(f: (Throwable) -> Kind<ForTry, A>): Kind<ForTry, A> =
                fix().handleErrorWith(f)

            override fun <A, B> tailRecM(
                a: A,
                f: (A) -> Kind<ForTry, Either<A, B>>
            ): Kind<ForTry, B> = Try.tailRecM(a, f)

            override fun <A, B> Kind<ForTry, A>.flatMap(f: (A) -> Kind<ForTry, B>): Kind<ForTry, B> =
                fix().flatMap(f)

            override val tickersService: TickersService
                get() = get()

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