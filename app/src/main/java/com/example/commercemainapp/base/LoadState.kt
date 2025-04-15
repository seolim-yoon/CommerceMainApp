package com.example.commercemainapp.base

sealed interface LoadState {
    data object Loading : LoadState
    data object Success : LoadState
    data class Error(val error: Throwable) : LoadState
}