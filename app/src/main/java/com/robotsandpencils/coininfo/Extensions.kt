package com.robotsandpencils.coininfo

import arrow.Kind
import arrow.core.NonFatal
import arrow.typeclasses.ApplicativeError

suspend fun <F, A> ApplicativeError<F, Throwable>.catchSuspend(f: suspend () -> A): Kind<F, A> =
    try {
        just(f())
    } catch (e: Throwable) {
        if (NonFatal(e)) {
            raiseError(e)
        } else {
            throw e
        }
    }
