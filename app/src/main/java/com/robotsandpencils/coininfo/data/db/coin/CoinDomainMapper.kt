package com.robotsandpencils.coininfo.data.db.coin

import arrow.Kind
import arrow.typeclasses.ApplicativeError
import com.robotsandpencils.coininfo.entities.Coin

interface CoinDomainMapper<F> : ApplicativeError<F, Throwable> {

    private fun CoinEntity.toCoin(): Coin = Coin(id, symbol, name, usdPrice)

    fun Kind<F, CoinEntity>.toCoinFromDatabase(): Kind<F, Coin> = map { it.toCoin() }

    fun Kind<F, List<CoinEntity>>.toCoinsFromDatabase(): Kind<F, List<Coin>> = map { entities ->
        entities.map { it.toCoin() }
    }

    private fun Coin.toEntity(): CoinEntity = CoinEntity(id, symbol, name, usdPrice)

    fun Kind<F, Coin>.toCoinForDatabase(): Kind<F, CoinEntity> = map { it.toEntity() }

    fun Kind<F, List<Coin>>.toCoinsForDatabase(): Kind<F, List<CoinEntity>> = map { coins ->
        coins.map { it.toEntity() }
    }
}