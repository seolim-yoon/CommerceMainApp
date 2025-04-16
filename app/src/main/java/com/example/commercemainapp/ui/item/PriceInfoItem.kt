package com.example.commercemainapp.ui.item

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import com.example.commercemainapp.R
import com.example.commercemainapp.ui.theme.Red


@Composable
internal fun DiscountInfoItem(
    isVisible: Boolean,
    discountPercent: Int,
    discountedPrice: Int,
    originalPrice: Int
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        if (isVisible) {
            Text(
                text = stringResource(R.string.discount_percent, discountPercent),
                color = Red,
                fontWeight = FontWeight.Bold
            )

        }

        Text(
            text = stringResource(
                R.string.price_won,
                if (discountedPrice != 0) discountedPrice else originalPrice
            ),
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
internal fun PriceInfoItem(
    isVisible: Boolean,
    originalPrice: Int
) {
    Text(
        text = if (isVisible) stringResource(R.string.price_won, originalPrice) else stringResource(R.string.empty),
        textDecoration = TextDecoration.LineThrough
    )
}