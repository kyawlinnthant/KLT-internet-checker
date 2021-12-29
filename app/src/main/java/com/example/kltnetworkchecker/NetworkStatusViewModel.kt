package com.example.kltnetworkchecker

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class NetworkStatusViewModel @Inject constructor(
    networkStatusTracker: NetworkStatusTracker,
) : ViewModel() {

    private val _state = MutableStateFlow<MyState>(MyState.Nothing)
    val state get() = _state.asStateFlow()

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