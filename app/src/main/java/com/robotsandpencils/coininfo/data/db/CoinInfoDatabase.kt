package com.robotsandpencils.coininfo.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.robotsandpencils.coininfo.data.db.coin.CoinDao
import com.robotsandpencils.coininfo.data.db.coin.CoinEntity

@Database(entities = [CoinEntity::class], version = 1)
abstract class CoinInfoDatabase : RoomDatabase() {

    abstract fun coinDao(): CoinDao
}