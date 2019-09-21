package com.robotsandpencils.coininfo.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.robotsandpencils.coininfo.data.db.coin.CoinDao
import com.robotsandpencils.coininfo.data.db.coin.CoinEntity
import com.robotsandpencils.coininfo.data.db.market.MarketDao
import com.robotsandpencils.coininfo.data.db.market.MarketEntity

@Database(
    entities = [
        CoinEntity::class,
        MarketEntity::class
    ], version = 1
)
abstract class CoinInfoDatabase : RoomDatabase() {

    abstract fun coinDao(): CoinDao

    abstract fun marketDao(): MarketDao
}