package com.example.commercemainapp.util

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.example.commercemainapp.model.ProductUiModel


class ProductParameterProvider(
    override val values: Sequence<ProductUiModel> = sequenceOf(
        discountCase,
        noDiscountCase,
        favoriteCase,
        longNameCase
    )
) : PreviewParameterProvider<ProductUiModel>

private val discountCase = ProductUiModel(
    id = 5052203,
    name = "[광화문 미진] 메밀국수 (2인분)",
    image = "https://img-cf.kurly.com/shop/data/goods/1653036058150l0.jpeg",
    originalPrice = 10000,
    discountedPrice = 8500,
    isSoldOut = false,
    isFavorite = false
)

private val noDiscountCase = ProductUiModel(
    id = 5052203,
    name = "[광화문 미진] 메밀국수 (2인분)",
    image = "https://img-cf.kurly.com/shop/data/goods/1653036058150l0.jpeg",
    originalPrice = 10000,
    isSoldOut = false,
    isFavorite = false
)

private val favoriteCase = ProductUiModel(
    id = 5052203,
    name = "[광화문 미진] 메밀국수 (2인분)",
    image = "https://img-cf.kurly.com/shop/data/goods/1653036058150l0.jpeg",
    originalPrice = 10000,
    isSoldOut = false,
    isFavorite = true
)

private val longNameCase = ProductUiModel(
    id = 5052203,
    name = "[광화문 미진] 메밀국수 (2인분) [광화문 미진] 메밀국수 (2인분) [광화문 미진] 메밀국수 (2인분) [광화문 미진] 메밀국수 (2인분) [광화문 미진] 메밀국수 (2인분) [광화문 미진] 메밀국수 (2인분)",
    image = "https://img-cf.kurly.com/shop/data/goods/1653036058150l0.jpeg",
    originalPrice = 10000,
    isSoldOut = false,
    isFavorite = true
)
