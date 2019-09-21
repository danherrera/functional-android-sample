package com.robotsandpencils.coininfo.data.db.market

import arrow.Kind
import arrow.typeclasses.ApplicativeError
import com.robotsandpencils.coininfo.entities.Market

interface MarketDomainMapper<F> : ApplicativeError<F, Throwable> {

    private fun MarketEntity.toMarket(): Market =
        Market(name, baseCurrency, quoteCurrency, price.toBigDecimal())

    fun Kind<F, MarketEntity>.toMarketFromDatabase(): Kind<F, Market> = map { it.toMarket() }

    fun Kind<F, List<MarketEntity>>.toMarketsFromDatabase(): Kind<F, List<Market>> =
        map { entities -> entities.map { it.toMarket() } }

    private fun Market.toEntity(): MarketEntity =
        MarketEntity(0, name, baseCurrency, quoteCurrency, price.toDouble())

    fun Kind<F, Market>.toMarketForDatabase(): Kind<F, MarketEntity> = map { it.toEntity() }

    fun Kind<F, List<Market>>.toMarketsForDatabase(): Kind<F, List<MarketEntity>> =
        map { markets -> markets.map { it.toEntity() } }
}