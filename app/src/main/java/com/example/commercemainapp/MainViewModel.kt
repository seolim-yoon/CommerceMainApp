package com.example.commercemainapp

import androidx.lifecycle.viewModelScope
import com.example.commercemainapp.base.BaseViewModel
import com.example.commercemainapp.base.LoadState
import com.example.commercemainapp.mapper.UiModelMapper
import com.example.commercemainapp.model.ProductUiModel
import com.example.commercemainapp.model.SectionUiModel
import com.example.commercemainapp.ui.contract.MainUiEvent
import com.example.commercemainapp.ui.contract.MainUiState
import com.example.data.repository.SectionRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val sectionRepository: SectionRepository,
    private val uiModelMapper: UiModelMapper
) : BaseViewModel<MainUiState, MainUiEvent>() {
    override fun createInitialState(): MainUiState = MainUiState()

    override fun handleException(throwable: Throwable) {
        setState {
            copy(
                loadState = LoadState.Error(throwable)
            )
        }
    }

    init {
        getSectionList()
    }

    private fun getSectionList() {
        viewModelScope.launch {
            sectionRepository.getSectionList(currentState.currentPage).collect { sectionDto ->
                sectionDto.sectionDataList.forEach { section ->
                    sectionRepository.getProductList(section.id).collect { productList ->
                        setState {
                            copy(
                                loadState = LoadState.Success,
                                currentPage = sectionDto.paging.nextPage,
                                sectionList = sectionList.toMutableList().apply {

                                    add(
                                        uiModelMapper.mapToSectionUiModel(
                                            sectionData = section,
                                            productList = uiModelMapper.mapToProductUiModelList(
                                                productList
                                            )
                                        )
                                    )
                                }
                            )
                        }
                    }
                }
            }
        }
    }

    private fun refresh() {
        setState {
            copy(
                loadState = LoadState.Loading,
                currentPage = 1
            )
        }
        getSectionList()
    }

    private fun clickFavorite(sectionUiModel: SectionUiModel, productUiModel: ProductUiModel) {
        setState {
            copy(
                sectionList = sectionList.map { section ->
                    if (section.id != sectionUiModel.id) return@map section

                    section.copy(
                        productList = section.productList.map { product ->
                            if (product.id == productUiModel.id) {
                                product.copy(
                                    isFavorite = !product.isFavorite
                                )
                            } else product
                        }
                    )
                }
            )
        }
    }

    override fun onEvent(event: MainUiEvent) {
        when (event) {
            is MainUiEvent.LoadMore -> {
                getSectionList()
            }

            is MainUiEvent.Refresh -> {
                refresh()
            }

            is MainUiEvent.ClickFavorite -> {
                clickFavorite(
                    sectionUiModel = event.sectionUiModel,
                    productUiModel = event.productUiModel
                )
            }
        }
    }
}