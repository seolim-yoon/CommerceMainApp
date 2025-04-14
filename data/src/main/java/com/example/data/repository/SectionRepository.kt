package com.example.data.repository

import com.example.data.api.SectionApi
import com.example.data.dto.ProductDTO
import com.example.data.dto.SectionDTO
import javax.inject.Inject

class SectionRepository @Inject constructor(
    private val sectionApi: SectionApi
){
    suspend fun getSectionList(page: Int): SectionDTO = sectionApi.getSectionList(page)

    suspend fun getProductList(sectionId: Int): ProductDTO = sectionApi.getProductList(sectionId)
}