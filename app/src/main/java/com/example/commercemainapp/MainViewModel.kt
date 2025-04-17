package com.example.commercemainapp

import com.example.commercemainapp.base.BaseViewModel
import com.example.commercemainapp.base.LoadState
import com.example.commercemainapp.mapper.UiModelMapper
import com.example.commercemainapp.model.ProductUiModel
import com.example.commercemainapp.model.SectionUiModel
import com.example.commercemainapp.ui.contract.MainUiEvent
import com.example.commercemainapp.ui.contract.MainUiState
import com.example.data.repository.SectionRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val sectionRepository: SectionRepository,
    private val uiModelMapper: UiModelMapper
) : BaseViewModel<MainUiState, MainUiEvent>() {
    private var isLoadingPaging = false
    private var currentPage = 1

    override fun createInitialState(): MainUiState = MainUiState()

    override fun handleException(throwable: Throwable) {
        setState {
            copy(
                isLoadMore = false,
                loadState = LoadState.Error(throwable)
            )
        }
    }

    init {
        getSectionList()
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    private fun getSectionList() {
        if (isLoadingPaging) return

        viewModelLaunch {
            isLoadingPaging = true
            sectionRepository.getSectionList(currentPage).flatMapConcat { pair ->
                val nextPage = pair.first
                val section = pair.second

                currentPage = nextPage

                sectionRepository.getProductList(sectionId = section.id).map { product ->
                    section to product
                }
            }.collect { (section, product) ->
                setState {
                    copy(
                        loadState = LoadState.Success,
                        isLoadMore = false,
                        sectionList = sectionList.toMutableList().apply {
                            add(
                                uiModelMapper.mapToSectionUiModel(
                                    sectionData = section,
                                    productList = uiModelMapper.mapToProductUiModelList(
                                        product
                                    )
                                )
                            )
                        }
                    )
                }
            }

            isLoadingPaging = false
        }
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
                setState {
                    copy(
                        isLoadMore = true
                    )
                }
                getSectionList()
            }

            is MainUiEvent.Refresh -> {
                currentPage = 1
                setState {
                    copy(
                        loadState = LoadState.Loading
                    )
                }
                getSectionList()
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