package com.example.commercemainapp.ui.item

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.example.commercemainapp.model.ProductUiModel
import com.example.commercemainapp.ui.theme.CommerceMainAppTheme
import com.example.commercemainapp.util.ProductParameterProvider

@Composable
internal fun HorizontalItem(
    product: ProductUiModel
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(4.dp),
        modifier = Modifier
            .width(150.dp)
            .padding(8.dp)
    ) {
     
        ProductImageItem(
            isFavorite = product.isFavorite,
            imageUrl = product.image,
            modifier = Modifier.size(width = 150.dp, height = 200.dp)
        )

        Text(
            text = product.name,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis
        )

        DiscountInfoItem(
            discountPercent = product.discountPercent,
            discountedPrice = product.discountedPrice,
            originalPrice = product.originalPrice
        )

        PriceInfoItem(
            isVisible = product.discountedPrice != 0,
            originalPrice = product.originalPrice
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewHorizontalListItem(@PreviewParameter(ProductParameterProvider::class) product: ProductUiModel) {
    CommerceMainAppTheme {
        HorizontalItem(
            product = product
        )
    }
}