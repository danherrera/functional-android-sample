package com.robotsandpencils.coininfo.presentation.coins

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import arrow.fx.ForIO
import arrow.fx.fix
import com.robotsandpencils.coininfo.data.RepositoryOperations
import com.robotsandpencils.coininfo.entities.Coin
import com.robotsandpencils.coininfo.presentation.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CoinsViewModel(
    private val repositoryOperations: RepositoryOperations<ForIO>
) : BaseViewModel() {

    private val _coins = MutableLiveData<List<Coin>>()
    val coins: LiveData<List<Coin>> = _coins

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    fun getData() = viewModelScope.launch {
        _isLoading.value = true
        withContext(Dispatchers.IO) {
            repositoryOperations.getAllCoins().fix()
                .attempt()
                .suspended()
        }.fold({
            println("")
        }, {
            _coins.value = it
        })
        _isLoading.value = false
    }
}