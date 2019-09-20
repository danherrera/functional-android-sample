package com.robotsandpencils.coininfo.data.db

import arrow.Kind
import arrow.typeclasses.MonadError
import com.robotsandpencils.coininfo.data.db.coin.CoinDao
import com.robotsandpencils.coininfo.data.db.coin.CoinEntity

interface DatabaseOperations<F> : MonadError<F, Throwable> {
    val coinDao: CoinDao

    fun queryCoins(): Kind<F, List<CoinEntity>> =
        catch { coinDao.getAll() }

    fun saveCoins(coins: List<CoinEntity>): Kind<F, Unit> =
        catch { coinDao.insertAll(coins) }
}