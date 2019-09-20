package com.robotsandpencils.coininfo.data.network

import arrow.Kind
import arrow.typeclasses.MonadError
import com.robotsandpencils.coininfo.entities.Coin
import com.robotsandpencils.coininfo.entities.Market

interface NetworkDomainMapper<F> : MonadError<F, Throwable> {

    fun Kind<F, List<TickerItemDto>>.toCoinsFromNetwork(): Kind<F, List<Coin>> =
        flatMap { tickers ->
            catch { tickers.map { it.toCoin() } }
        }

    fun Kind<F, List<CoinMarketDto>>.toMarketsFromNetwork(): Kind<F, List<Market>> =
        flatMap { markets ->
            catch { markets.map { it.toMarket() } }
        }

    private fun TickerItemDto.toCoin(): Coin =
        Coin(id, symbol, name, price_usd)

    private fun CoinMarketDto.toMarket(): Market =
        Market(name, base, quote, price_usd.toBigDecimal())
}