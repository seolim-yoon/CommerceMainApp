package com.example.commercemainapp.ui.item

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.example.commercemainapp.model.ProductUiModel
import com.example.commercemainapp.ui.theme.CommerceMainAppTheme
import com.example.commercemainapp.util.ProductParameterProvider

@Composable
internal fun VerticalListItem(
    product: ProductUiModel
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(4.dp),
        modifier = Modifier
            .padding(8.dp)
    ) {

        ProductImageItem(
            isFavorite = product.isFavorite,
            imageUrl = product.image,
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(6f / 4f)
        )

        Text(
            text = product.name,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
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
}

@Preview(showBackground = true)
@Composable
private fun PreviewVerticalListItem(@PreviewParameter(ProductParameterProvider::class) product: ProductUiModel) {
    CommerceMainAppTheme {
        VerticalListItem(
            product = product
        )
    }
}
