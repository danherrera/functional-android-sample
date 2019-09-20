package com.robotsandpencils.coininfo.presentation.coindetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.viewModelScope
import arrow.fx.ForIO
import arrow.fx.IO
import arrow.fx.extensions.fx
import com.robotsandpencils.coininfo.data.RequestOperations
import com.robotsandpencils.coininfo.entities.Market
import com.robotsandpencils.coininfo.presentation.BaseViewModel
import com.robotsandpencils.coininfo.presentation.common.StartEndTextViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.math.RoundingMode

class CoinDetailsViewModel(
    private val requestOperations: RequestOperations<ForIO>
) : BaseViewModel() {

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

    fun getMarkets(coinId: String) = viewModelScope.launch {
        IO.fx {
            continueOn(Dispatchers.IO)
            val markets = !requestOperations.getMarketsForCoin(coinId)
            continueOn(Dispatchers.Main)
            _markets.value = markets
        }.suspended()
    }
}