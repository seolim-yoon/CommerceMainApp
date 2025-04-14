package com.example.commercemainapp.model

data class ProductUiModel(
    val id: Int = 0,
    val name: String = "",
    val image: String = "",
    val originalPrice: Int = 0,
    val discountedPrice: Int = 0,
    val isSoldOut: Boolean = false,
    val isFavorite: Boolean = false
) {
    val discountPercent = ((1 - (discountedPrice.toDouble() / originalPrice.toDouble())) * 100).toInt()
}
