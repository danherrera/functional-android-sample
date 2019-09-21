package com.robotsandpencils.coininfo.presentation.coindetails

import androidx.lifecycle.viewModelScope
import arrow.core.Option
import arrow.fx.ForIO
import arrow.fx.fix
import com.robotsandpencils.coininfo.data.RepositoryOperations
import com.robotsandpencils.coininfo.presentation.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CoinDetailsViewModel(
    private val repositoryOperations: RepositoryOperations<ForIO>
) : BaseViewModel<CoinDetailsState>(CoinDetailsState()) {

    fun getMarkets(coinId: String) = viewModelScope.launch {
        updateState { copy(isLoading = true) }
        withContext(Dispatchers.IO) {
            repositoryOperations.getMarketsForCoin(coinId).fix()
                .attempt()
                .suspended()
        }.fold({
            updateState { copy(error = Option.just(it)) }
        }, {
            updateState { copy(markets = it, error = Option.empty()) }
        })
        updateState { copy(isLoading = false) }
    }
}