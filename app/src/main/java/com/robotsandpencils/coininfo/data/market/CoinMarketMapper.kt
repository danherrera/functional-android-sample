package com.robotsandpencils.coininfo.data.market

import com.robotsandpencils.coininfo.entities.Market

class CoinMarketMapper {

    fun map(market: CoinMarketDto): Market {
        return market.run { Market(name, base, quote, price_usd.toBigDecimal()) }
    }
}