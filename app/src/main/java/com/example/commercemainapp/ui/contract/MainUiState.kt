package com.example.commercemainapp.ui.contract

import com.example.commercemainapp.model.SectionUiModel

data class MainUiState(
    val loadState: LoadState = LoadState.Success,
    val sectionList: List<SectionUiModel> = emptyList()
)

sealed interface LoadState {
    data object Loading : LoadState
    data object Success : LoadState
    data class Error(val error: Throwable) : LoadState
}