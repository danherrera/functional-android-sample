package com.robotsandpencils.coininfo.data.db.coin

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface CoinDao {

    @Query("SELECT * FROM coins")
    fun getAll(): List<CoinEntity>

    @Query("SELECT * FROM coins WHERE id LIKE :id")
    fun getById(id: String): CoinEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(coin: CoinEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(coins: List<CoinEntity>)
}