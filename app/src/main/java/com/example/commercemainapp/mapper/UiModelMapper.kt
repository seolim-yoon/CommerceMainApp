package com.example.commercemainapp.mapper

import com.example.commercemainapp.model.ProductUiModel
import com.example.commercemainapp.model.SectionUiModel
import com.example.commercemainapp.util.SectionType
import com.example.data.dto.ProductDTO
import com.example.data.dto.SectionDTO
import javax.inject.Inject

class UiModelMapper @Inject constructor() {
    fun mapToSectionUiModelList(sectionDataList: List<SectionDTO.SectionData>): List<SectionUiModel> =
        sectionDataList.map { sectionData ->
            SectionUiModel(
                id = sectionData.id,
                title = sectionData.title,
                type = SectionType.fromValueByString(sectionData.type)
            )
        }

    fun mapToSectionUiModel(sectionData: SectionDTO.SectionData, productList: List<ProductUiModel>): SectionUiModel =
        SectionUiModel(
            id = sectionData.id,
            title = sectionData.title,
            type = SectionType.fromValueByString(sectionData.type),
            productList = productList
        )

    fun mapToProductUiModelList(productDataList: List<ProductDTO.ProductData>): List<ProductUiModel> =
        productDataList.map { productData ->
            ProductUiModel(
                id = productData.id,
                name = productData.name,
                image = productData.image,
                originalPrice = productData.originalPrice,
                discountedPrice = productData.discountedPrice,
                isSoldOut = productData.isSoldOut,
                isFavorite = false
            )
        }
}