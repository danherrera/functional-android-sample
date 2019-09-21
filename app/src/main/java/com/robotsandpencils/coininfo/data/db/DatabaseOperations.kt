package com.robotsandpencils.coininfo.data.db

import com.robotsandpencils.coininfo.data.db.coin.CoinDatabaseOperations
import com.robotsandpencils.coininfo.data.db.market.MarketDatabaseOperations

interface DatabaseOperations<F> : CoinDatabaseOperations<F>, MarketDatabaseOperations<F>