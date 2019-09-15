package com.robotsandpencils.coininfo.presentation.coindetails

import androidx.lifecycle.*
import com.robotsandpencils.coininfo.domain.market.GetMarketsForCoinUseCase
import com.robotsandpencils.coininfo.entities.Market
import com.robotsandpencils.coininfo.presentation.common.StartEndTextViewModel
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.math.RoundingMode

class CoinDetailsViewModel(
    private val getMarketsForCoinUseCase: GetMarketsForCoinUseCase
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
            _markets.value = withContext(IO) { getMarketsForCoinUseCase.getMarketsForCoin(coinId) }
        }
    }
}