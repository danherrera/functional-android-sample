package com.robotsandpencils.coininfo.presentation.coins

import androidx.lifecycle.viewModelScope
import arrow.core.Option
import arrow.fx.ForIO
import arrow.fx.fix
import com.robotsandpencils.coininfo.data.RepositoryOperations
import com.robotsandpencils.coininfo.presentation.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CoinsViewModel(
    private val repositoryOperations: RepositoryOperations<ForIO>
) : BaseViewModel<CoinsState>(CoinsState()) {

    fun getData() = viewModelScope.launch {

        updateState { copy(isLoading = false) }

        withContext(Dispatchers.IO) {

            repositoryOperations.getAllCoins().fix()
                .attempt()
                .suspended()

        }.fold({

            updateState { copy(error = Option.just(it)) }

        }, {

            updateState { copy(coins = it, error = Option.empty()) }

        })

        updateState { copy(isLoading = false) }
    }
}