package com.robotsandpencils.coininfo.presentation.coins

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.robotsandpencils.coininfo.domain.allcoins.GetAllCoinsUseCase
import com.robotsandpencils.coininfo.entities.Coin
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CoinsViewModel(
    private val getAllCoinsUseCase: GetAllCoinsUseCase
) : ViewModel() {

    private val _coins = MutableLiveData<List<Coin>>()
    val coins: LiveData<List<Coin>> = _coins

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    fun getData() {
        viewModelScope.launch {
            update(_isLoading) {
                _coins.value = withContext(IO) { getAllCoinsUseCase.getAllCoins() }
            }
        }
    }

    private suspend fun update(isLoading: MutableLiveData<Boolean>, block: suspend () -> Unit) {
        isLoading.value = true
        block()
        isLoading.value = false
    }
}