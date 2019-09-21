package com.robotsandpencils.coininfo.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

abstract class BaseViewModel<S>(initialState: S) : ViewModel() {

    private val _state = MutableLiveData<S>().apply { value = initialState }
    val state: LiveData<S> = _state

    protected fun updateState(ff: S.() -> S) {
        _state.value = ff(_state.value!!)
    }
}
