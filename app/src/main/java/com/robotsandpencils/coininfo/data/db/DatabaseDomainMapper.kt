package com.robotsandpencils.coininfo.data.db

import arrow.Kind
import arrow.typeclasses.MonadError
import com.robotsandpencils.coininfo.data.db.coin.CoinEntity
import com.robotsandpencils.coininfo.entities.Coin

interface DatabaseDomainMapper<F> : MonadError<F, Throwable> {

    fun Kind<F, List<CoinEntity>>.toCoinsFromDatabase(): Kind<F, List<Coin>> = flatMap { entities ->
        catch { entities.map { it.toCoin() } }
    }

    fun Kind<F, List<Coin>>.toCoinsForDatabase(): Kind<F, List<CoinEntity>> = map { coins ->
        coins.map { it.toEntity() }
    }

    private fun CoinEntity.toCoin(): Coin = Coin(id, symbol, name, usdPrice)

    private fun Coin.toEntity(): CoinEntity = CoinEntity(id, symbol, name, usdPrice)
}