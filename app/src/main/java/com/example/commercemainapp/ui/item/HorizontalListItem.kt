package com.example.commercemainapp.ui.item

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.commercemainapp.model.ProductUiModel
import com.example.commercemainapp.util.HORIZONTAL_ITEM_TYPE

@Composable
internal fun HorizontalListItem(
    productList: List<ProductUiModel>,
    onClickFavorite: (ProductUiModel) -> Unit
) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        items(
            key = { it.id },
            contentType = { HORIZONTAL_ITEM_TYPE },
            items = productList
        ) { product ->
            HorizontalItem(
                product = product,
                onClickFavorite = {
                    onClickFavorite(product)
                }
            )
        }
    }
}