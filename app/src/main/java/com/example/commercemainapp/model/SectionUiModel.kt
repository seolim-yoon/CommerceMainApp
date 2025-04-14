package com.example.commercemainapp.model

data class SectionUiModel(
    val id: Int = 0,
    val title: String = "",
    val type: String = "",
    val productList: List<ProductUiModel> = emptyList()
)
