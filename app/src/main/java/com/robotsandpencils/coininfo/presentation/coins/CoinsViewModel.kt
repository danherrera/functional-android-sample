package com.robotsandpencils.coininfo.presentation.coins

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import arrow.fx.ForIO
import arrow.fx.IO
import arrow.fx.extensions.fx
import com.robotsandpencils.coininfo.data.RequestOperations
import com.robotsandpencils.coininfo.entities.Coin
import com.robotsandpencils.coininfo.presentation.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CoinsViewModel(
    private val requestOperations: RequestOperations<ForIO>
) : BaseViewModel() {

    private val _coins = MutableLiveData<List<Coin>>()
    val coins: LiveData<List<Coin>> = _coins

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    fun getData() = viewModelScope.launch {
        IO.fx {
            _isLoading.value = true
            continueOn(Dispatchers.IO)
            val coins = !requestOperations.getAllCoins()
            continueOn(Dispatchers.Main)
            _coins.value = coins
            _isLoading.value = false
        }.suspended()
    }
}