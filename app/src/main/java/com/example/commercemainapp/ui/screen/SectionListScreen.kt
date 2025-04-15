package com.example.commercemainapp.ui.screen

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.commercemainapp.model.SectionUiModel
import com.example.commercemainapp.ui.item.SectionListItem

@Composable
internal fun SectionListScreen(
    sectionList: List<SectionUiModel>,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
    ) {
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