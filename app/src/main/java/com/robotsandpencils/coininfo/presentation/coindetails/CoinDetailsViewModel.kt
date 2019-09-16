package com.robotsandpencils.coininfo.presentation.coindetails

import androidx.lifecycle.*
import arrow.core.ForTry
import arrow.core.fix
import com.robotsandpencils.coininfo.data.RequestOperations
import com.robotsandpencils.coininfo.entities.Market
import com.robotsandpencils.coininfo.presentation.common.StartEndTextViewModel
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.math.RoundingMode

class CoinDetailsViewModel(
    private val requestOperations: RequestOperations<ForTry>
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
        viewModelScope.launch {
            withContext(IO) { requestOperations.getMarketsForCoin(coinId).fix() }
                .fold(
                    ifFailure = {
                    },
                    ifSuccess = {
                        _markets.value = it
                    })
        }
    }
}