package com.example.commercemainapp.ui.contract

import com.example.commercemainapp.base.UiEvent
import com.example.commercemainapp.model.ProductUiModel

sealed class MainUiEvent: UiEvent {
    data class LoadMore(val page: Int): MainUiEvent()
    data object Refresh: MainUiEvent()
    data class ClickFavorite(val productUiModel: ProductUiModel): MainUiEvent()
}