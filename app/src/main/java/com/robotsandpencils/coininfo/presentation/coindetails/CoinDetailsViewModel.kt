package com.robotsandpencils.coininfo.presentation.coindetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import arrow.fx.ForIO
import arrow.fx.IO
import arrow.fx.fix
import com.robotsandpencils.coininfo.data.RequestOperations
import com.robotsandpencils.coininfo.entities.Market
import com.robotsandpencils.coininfo.presentation.common.StartEndTextViewModel
import kotlinx.coroutines.Dispatchers
import java.math.RoundingMode

class CoinDetailsViewModel(
    private val requestOperations: RequestOperations<ForIO>
) : ViewModel() {

    private val _markets = MutableLiveData<List<Market>>()
    val markets: LiveData<List<StartEndTextViewModel>> = Transformations.map(_markets) { list ->
        list.map {
            StartEndTextViewModel(
                it.name,
                "${it.price.setScale(
                    2,
                    RoundingMode.HALF_UP
                )} ${it.quoteCurrency}/${it.baseCurrency}"
            )
        }
    }

    fun getMarkets(coinId: String) {
        IO(Dispatchers.IO) {
            requestOperations.getMarketsForCoin(coinId).fix()
                .continueOn(Dispatchers.Main)
                .unsafeRunAsync {
                    it.fold({
                        println("")
                    }, {
                        _markets.value = it
                    })
                }
        }.unsafeRunSync()
    }
}