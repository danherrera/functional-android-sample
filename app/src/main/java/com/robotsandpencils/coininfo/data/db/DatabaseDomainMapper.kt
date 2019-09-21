package com.robotsandpencils.coininfo.data.db

import com.robotsandpencils.coininfo.data.db.coin.CoinDomainMapper
import com.robotsandpencils.coininfo.data.db.market.MarketDomainMapper

interface DatabaseDomainMapper<F> : CoinDomainMapper<F>, MarketDomainMapper<F>