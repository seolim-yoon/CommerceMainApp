package com.example.commercemainapp.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.unit.dp
import com.example.commercemainapp.model.ProductUiModel
import com.example.commercemainapp.model.SectionUiModel
import com.example.commercemainapp.ui.item.GridListItem
import com.example.commercemainapp.ui.item.HorizontalListItem
import com.example.commercemainapp.ui.item.LoadMoreItem
import com.example.commercemainapp.ui.item.SectionTitleItem
import com.example.commercemainapp.ui.item.VerticalItem
import com.example.commercemainapp.util.LOAD_MORE_ITEM_TYPE
import com.example.commercemainapp.util.SectionType
import com.example.commercemainapp.util.VERTICAL_ITEM_TYPE

@Composable
internal fun SectionListScreen(
    sectionList: List<SectionUiModel>,
    isLoadMore: Boolean,
    onClickFavorite: (SectionUiModel, ProductUiModel) -> Unit,
    loadMoreItem: () -> Unit
) {
    val listState = rememberLazyListState()
    val needLoadMore by remember {
        derivedStateOf {
            val totalItemsCount = listState.layoutInfo.totalItemsCount
            val lastVisibleItemIndex = listState.layoutInfo.visibleItemsInfo.lastOrNull()?.index ?: 0
            lastVisibleItemIndex != 0 && lastVisibleItemIndex >= totalItemsCount - 1
        }
    }

    LaunchedEffect(needLoadMore) {
        if (needLoadMore) {
            loadMoreItem()
        }
    }

    LazyColumn(
        state = listState,
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        sectionList.forEach { section ->
            item {
                SectionTitleItem(
                    title = section.title
                )
            }

            when (section.type) {
                SectionType.HORIZONTAL -> {
                    item (
                        key = section.id,
                        contentType = { section.type }
                    ) {
                        HorizontalListItem(
                            productList = section.productList,
                            onClickFavorite = { product ->
                                onClickFavorite(section, product)
                            }
                        )
                    }
                }

                SectionType.VERTICAL -> {
                    items(
                        key = { it.id },
                        contentType = { VERTICAL_ITEM_TYPE },
                        items = section.productList
                    ) { product ->
                        VerticalItem(
                            product = product,
                            onClickFavorite = {
                                onClickFavorite(section, product)
                            }
                        )
                    }
                }

                SectionType.GRID -> {
                    item(
                        key = section.id,
                        contentType = { section.type }
                    ) {
                        GridListItem(
                            productList = section.productList,
                            onClickFavorite = { product ->
                                onClickFavorite(section, product)
                            }
                        )
                    }
                }
            }
        }

        if (isLoadMore) {
            item(
                contentType = { LOAD_MORE_ITEM_TYPE }
            ) {
                LoadMoreItem()
            }
        }
    }
}