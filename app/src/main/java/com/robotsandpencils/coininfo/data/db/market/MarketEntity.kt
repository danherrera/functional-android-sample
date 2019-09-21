package com.robotsandpencils.coininfo.data.db.market

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "markets",
    indices = [Index("name", "baseCurrency", "quoteCurrency", unique = true)]
)
data class MarketEntity(
    @PrimaryKey(autoGenerate = true) val uid: Long,
    val name: String,
    val baseCurrency: String,
    val quoteCurrency: String,
    val price: Double
)