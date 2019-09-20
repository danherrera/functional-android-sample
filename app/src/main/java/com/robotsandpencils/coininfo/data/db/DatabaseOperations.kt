package com.robotsandpencils.coininfo.data.db

import arrow.Kind
import arrow.typeclasses.ApplicativeError
import com.robotsandpencils.coininfo.data.db.coin.CoinDao
import com.robotsandpencils.coininfo.data.db.coin.CoinEntity

interface DatabaseOperations<F> : ApplicativeError<F, Throwable> {
    val coinDao: CoinDao

    fun queryCoins(): Kind<F, List<CoinEntity>> =
        catch { coinDao.getAll() }

    fun saveCoins(coins: List<CoinEntity>): Kind<F, Unit> =
        catch { coinDao.insertAll(coins) }
}