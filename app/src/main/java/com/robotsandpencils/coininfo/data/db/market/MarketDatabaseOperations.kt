package com.robotsandpencils.coininfo.data.db.market

import arrow.Kind
import arrow.typeclasses.ApplicativeError

interface MarketDatabaseOperations<F> : ApplicativeError<F, Throwable> {
    val marketDao: MarketDao

    fun queryMarkets(): Kind<F, List<MarketEntity>> =
        catch { marketDao.getAll() }

    fun queryMarketBySymbol(symbol: String): Kind<F, List<MarketEntity>> =
        catch { marketDao.getBySymbol(symbol) }

    fun saveMarkets(markets: List<MarketEntity>): Kind<F, Unit> =
        catch { marketDao.insertAll(markets) }
}