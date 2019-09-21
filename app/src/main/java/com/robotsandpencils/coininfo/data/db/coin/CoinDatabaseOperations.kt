package com.robotsandpencils.coininfo.data.db.coin

import arrow.Kind
import arrow.typeclasses.ApplicativeError

interface CoinDatabaseOperations<F> : ApplicativeError<F, Throwable> {
    val coinDao: CoinDao

    fun queryCoins(): Kind<F, List<CoinEntity>> =
        catch { coinDao.getAll() }

    fun queryCoin(coinId: String): Kind<F, CoinEntity> =
        catch { coinDao.getById(coinId) }

    fun saveCoins(coins: List<CoinEntity>): Kind<F, Unit> =
        catch { coinDao.insertAll(coins) }
}