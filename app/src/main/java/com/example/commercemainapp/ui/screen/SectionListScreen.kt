package com.example.commercemainapp.ui.screen

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import com.example.commercemainapp.model.SectionUiModel
import com.example.commercemainapp.ui.item.SectionListItem

@Composable
internal fun SectionListScreen(
    sectionList: List<SectionUiModel>
) {
    LazyColumn {
        items(
            key = { it.id },
            contentType = { it.type },
            items = sectionList
        ) { section ->

            SectionListItem(
                section = section
            )
        }
    }
}