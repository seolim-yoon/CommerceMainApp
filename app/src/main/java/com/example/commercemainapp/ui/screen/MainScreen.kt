package com.example.commercemainapp.ui.screen

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.commercemainapp.base.LoadState
import com.example.commercemainapp.ui.contract.MainUiEvent
import com.example.commercemainapp.ui.contract.MainUiState

@Composable
internal fun MainScreen(
    state: MainUiState,
    onEvent: (MainUiEvent) -> Unit,
    modifier: Modifier = Modifier
) {
    when (state.loadState) {
        is LoadState.Loading -> {
            LoadingScreen(
                modifier = modifier
            )
        }

        is LoadState.Success -> {
            SectionListScreen(
                sectionList = state.sectionList,
                modifier = modifier
            )
        }

        is LoadState.Error -> {
            ErrorScreen(
                errorMessage = state.loadState.error.message.toString(),
                modifier = modifier
            )
        }
    }
}