package com.example.data.api

import com.example.data.dto.ProductDTO
import com.example.data.dto.SectionDTO
import retrofit2.http.GET
import retrofit2.http.Query

interface SectionApi {
    @GET("sections")
    suspend fun getSectionList(
        @Query("page") page: Int
    ): SectionDTO

    @GET("section/products")
    suspend fun getProductList(
        @Query("sectionId") sectionId: Int
    ): ProductDTO
}