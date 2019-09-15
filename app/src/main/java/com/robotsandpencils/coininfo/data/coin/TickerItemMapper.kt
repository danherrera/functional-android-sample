package com.robotsandpencils.coininfo.data.coin

import com.robotsandpencils.coininfo.entities.Coin

class TickerItemMapper {

    fun map(tickerItem: TickerItemDto): Coin {
        return tickerItem.run { Coin(id, symbol, name, price_usd) }
    }
}