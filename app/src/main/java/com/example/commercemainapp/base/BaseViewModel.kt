package com.example.commercemainapp.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

abstract class BaseViewModel<State: UiState, Event: UiEvent> : ViewModel() {
    private val initialState : State by lazy { createInitialState() }
    abstract fun createInitialState() : State

    val currentState: State
        get() = state.value

    private val _state: MutableStateFlow<State> = MutableStateFlow(initialState)
    val state = _state.asStateFlow()

    private val exceptionHandler = CoroutineExceptionHandler { _, exception ->
        handleException(exception)
    }

    abstract fun handleException(throwable: Throwable)

    protected fun setState(reduce: State.() -> State) {
        val newState = currentState.reduce()

        _state.update {
            newState
        }
    }

    abstract fun onEvent(event: Event)

    protected fun viewModelLaunch(
        onSuccess: suspend () -> Unit
    ) {
        viewModelScope.launch(
            context = exceptionHandler,
        ) {
            onSuccess.invoke()
        }
    }
}