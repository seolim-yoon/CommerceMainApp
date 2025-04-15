package com.example.data.repository

import android.util.Log
import com.example.data.api.SectionApi
import com.example.data.dto.ProductDTO
import com.example.data.dto.SectionDTO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import java.io.IOException
import javax.inject.Inject

class SectionRepository @Inject constructor(
    private val sectionApi: SectionApi
) {
    fun getSectionList(page: Int): Flow<SectionDTO> = flow {
            emit(sectionApi.getSectionList(page))
    }.catch { error ->
        if (error is IOException) error.stackTrace
        else throw error
    }

    fun getProductList(sectionId: Int): Flow<List<ProductDTO.ProductData>> = flow {
        emit(sectionApi.getProductList(sectionId).productDataList)
    }.catch { error ->
        if (error is IOException) error.stackTrace
        else throw error
    }
}