package com.robotsandpencils.coininfo.presentation.coins

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import arrow.core.ForTry
import arrow.core.fix
import com.robotsandpencils.coininfo.data.RequestOperations
import com.robotsandpencils.coininfo.entities.Coin
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CoinsViewModel(
    private val requestOperations: RequestOperations<ForTry>
) : ViewModel() {

    private val _coins = MutableLiveData<List<Coin>>()
    val coins: LiveData<List<Coin>> = _coins

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    fun getData() {
        viewModelScope.launch {
            update(_isLoading) {
                withContext(IO) { requestOperations.getAllCoins().fix() }
                    .fold(
                        ifFailure = {
                        },
                        ifSuccess = {
                            _coins.value = it
                        })
            }
        }
    }

    private suspend fun update(isLoading: MutableLiveData<Boolean>, block: suspend () -> Unit) {
        isLoading.value = true
        block()
        isLoading.value = false
    }
}