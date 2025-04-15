package com.example.data.dto


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ProductDTO(
    @SerialName("data")
    val productDataList: List<ProductData> = listOf()
) {
    @Serializable
    data class ProductData(
        @SerialName("discountedPrice")
        val discountedPrice: Int = 0,
        @SerialName("id")
        val id: Int = 0,
        @SerialName("image")
        val image: String = "",
        @SerialName("isSoldOut")
        val isSoldOut: Boolean = false,
        @SerialName("name")
        val name: String = "",
        @SerialName("originalPrice")
        val originalPrice: Int = 0
    )
}