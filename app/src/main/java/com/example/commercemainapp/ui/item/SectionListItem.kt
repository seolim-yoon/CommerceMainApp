package com.example.commercemainapp.ui.item

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.commercemainapp.model.SectionUiModel
import com.example.commercemainapp.ui.theme.Red
import com.example.commercemainapp.util.HORIZONTAL_ITEM_TYPE
import com.example.commercemainapp.util.SectionType
import com.example.commercemainapp.util.VERTICAL_ITEM_TYPE

@Composable
internal fun SectionListItem(
    section: SectionUiModel
) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = section.title,
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = Red
                ).padding(8.dp)
        )

        when(section.type) {
            SectionType.HORIZONTAL -> {
                LazyRow(
                    contentPadding = PaddingValues(8.dp),
                    horizontalArrangement = Arrangement.spacedBy(4.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    items(
                        key = { it.id },
                        contentType = { HORIZONTAL_ITEM_TYPE },
                        items = section.productList
                    ) { product ->
                        HorizontalItem(
                            product = product
                        )
                    }
                }
            }
            SectionType.VERTICAL -> {
                LazyColumn(
                    contentPadding = PaddingValues(8.dp),
                    verticalArrangement = Arrangement.spacedBy(4.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    items(
                        key = { it.id },
                        contentType = { VERTICAL_ITEM_TYPE },
                        items = section.productList
                    ) { product ->
                        VerticalItem(
                            product = product
                        )
                    }
                }
            }
            SectionType.GRID -> {
                LazyHorizontalGrid(
                    rows = GridCells.Fixed(3),
                    contentPadding = PaddingValues(8.dp),
                    verticalArrangement = Arrangement.spacedBy(4.dp),
                    horizontalArrangement = Arrangement.spacedBy(4.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    items(
                        key = { it.id },
                        contentType = { HORIZONTAL_ITEM_TYPE },
                        items = section.productList
                    ) { product ->
                        HorizontalItem(
                            product = product
                        )
                    }
                }
            }
        }
    }
}