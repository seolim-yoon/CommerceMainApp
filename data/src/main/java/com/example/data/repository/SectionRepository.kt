package com.example.data.repository

import com.example.data.api.SectionApi
import com.example.data.dto.ProductDTO
import com.example.data.dto.SectionDTO
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SectionRepository @Inject constructor(
    private val sectionApi: SectionApi
) {
    fun getSectionList(page: Int): Flow<Pair<Int, SectionDTO.SectionData>> = flow {
        val sectionData = sectionApi.getSectionList(page)

        coroutineScope {
            sectionData.sectionDataList.map { section ->
                async {
                    sectionData.paging.nextPage to section
                }
            }.forEach { deferred ->
                emit(deferred.await())
            }
        }
    }

    fun getProductList(sectionId: Int): Flow<List<ProductDTO.ProductData>> = flow {
        emit(sectionApi.getProductList(sectionId).productDataList)
    }
}