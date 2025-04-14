package com.example.commercemainapp.model

import com.example.commercemainapp.util.SectionType

data class SectionUiModel(
    val id: Int = 0,
    val title: String = "",
    val type: SectionType = SectionType.HORIZONTAL,
    val productList: List<ProductUiModel> = emptyList()
)
