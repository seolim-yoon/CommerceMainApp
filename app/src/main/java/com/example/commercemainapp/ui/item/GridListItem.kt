package com.example.commercemainapp.ui.item

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.commercemainapp.model.ProductUiModel
import com.example.commercemainapp.util.HORIZONTAL_ITEM_TYPE

@Composable
internal fun GridListItem(
    productList: List<ProductUiModel>,
    onClickFavorite: (ProductUiModel) -> Unit
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        verticalArrangement = Arrangement.spacedBy(4.dp),
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(max = 2000.dp)
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