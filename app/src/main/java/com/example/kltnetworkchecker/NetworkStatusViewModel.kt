package com.example.kltnetworkchecker

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class NetworkStatusViewModel @Inject constructor(
    networkStatusTracker: NetworkStatusTracker,
) : ViewModel() {

    private val _state = MutableSharedFlow<MyState>()
    val state get() = _state.asSharedFlow()

    init {

        viewModelScope.launch {
            val s = networkStatusTracker.networkStatus.map(
                onAvailable = { MyState.Fetched },
                onUnavailable = { MyState.Error }
            )
            s.collect {
                _state.emit(it)
            }
        }
    }
}