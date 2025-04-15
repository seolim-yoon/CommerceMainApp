package com.example.commercemainapp.ui.contract

import com.example.commercemainapp.base.LoadState
import com.example.commercemainapp.base.UiState
import com.example.commercemainapp.model.SectionUiModel

data class MainUiState(
    val loadState: LoadState = LoadState.Success,
    val currentPage: Int = 1,
    val sectionList: List<SectionUiModel> = emptyList()
): UiState

