package com.example.kltnetworkchecker

sealed class MyState {
    object Fetched : MyState()
    object Error : MyState()
}
