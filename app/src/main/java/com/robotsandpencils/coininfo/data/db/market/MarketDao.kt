package com.robotsandpencils.coininfo.data.db.market

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface MarketDao {

    @Query("SELECT * FROM markets")
    fun getAll(): List<MarketEntity>

    @Query("SELECT * FROM markets WHERE (baseCurrency LIKE :symbol OR quoteCurrency LIKE :symbol)")
    fun getBySymbol(symbol: String): List<MarketEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(coins: List<MarketEntity>)
}