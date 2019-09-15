package com.robotsandpencils.coininfo.entities

import java.math.BigDecimal

data class Market(
    val name: String,
    val baseCurrency: String,
    val quoteCurrency: String,
    val price: BigDecimal
)