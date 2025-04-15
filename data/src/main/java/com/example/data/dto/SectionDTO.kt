package com.example.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SectionDTO(
    @SerialName("data")
    val sectionDataList: List<SectionData> = listOf(),
    @SerialName("paging")
    val paging: Paging = Paging()
) {
    @Serializable
    data class SectionData(
        @SerialName("id")
        val id: Int = 0,
        @SerialName("title")
        val title: String = "",
        @SerialName("type")
        val type: String = "",
        @SerialName("url")
        val url: String = ""
    )

    @Serializable
    data class Paging(
        @SerialName("next_page")
        val nextPage: Int = 0
    )
}