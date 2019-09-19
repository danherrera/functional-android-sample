package com.robotsandpencils.coininfo.presentation.coins

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import arrow.fx.ForIO
import arrow.fx.IO
import arrow.fx.fix
import com.robotsandpencils.coininfo.data.RequestOperations
import com.robotsandpencils.coininfo.entities.Coin
import kotlinx.coroutines.Dispatchers

class CoinsViewModel(
    private val requestOperations: RequestOperations<ForIO>
) : ViewModel() {

    private val _coins = MutableLiveData<List<Coin>>()
    val coins: LiveData<List<Coin>> = _coins

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val getAllCoinsIO = IO(Dispatchers.IO) {
        requestOperations.getAllCoins().fix()
            .continueOn(Dispatchers.Main)
            .unsafeRunAsync {
                _isLoading.value = false
                it.fold(
                    ifLeft = {
                        println("")
                    },
                    ifRight = {
                        _coins.value = it
                    })
            }
    }

    fun getData() {
        _isLoading.value = true
        getAllCoinsIO.unsafeRunSync()
    }
}