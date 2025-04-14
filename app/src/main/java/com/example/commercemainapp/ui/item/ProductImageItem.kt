package com.example.commercemainapp.ui.item

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.commercemainapp.R

@Composable
internal fun ProductImageItem(
    isFavorite: Boolean,
    imageUrl: String,
    modifier: Modifier = Modifier
) {
    Box {
        Image(
            painter = if (isFavorite) painterResource(R.drawable.ic_btn_heart_on)
            else painterResource(R.drawable.ic_btn_heart_off),
            contentDescription = null,
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(4.dp)
        )

        if (LocalInspectionMode.current) {
            Icon(
                imageVector = Icons.Default.AccountBox,
                contentDescription = null,
                modifier = modifier
            )
        } else {
            AsyncImage(
                model = imageUrl,
                contentDescription = null,
                modifier = modifier
            )
        }
    }
}