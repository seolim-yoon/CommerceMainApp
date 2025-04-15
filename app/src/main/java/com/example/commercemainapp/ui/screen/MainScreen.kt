package com.example.commercemainapp.ui.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.commercemainapp.base.LoadState
import com.example.commercemainapp.ui.contract.MainUiEvent
import com.example.commercemainapp.ui.contract.MainUiState

@OptIn(ExperimentalMaterialApi::class)
@Composable
internal fun MainScreen(
    state: MainUiState,
    onEvent: (MainUiEvent) -> Unit,
    modifier: Modifier = Modifier
) {
    val pullToRefreshState = rememberPullRefreshState(
        refreshing = state.loadState is LoadState.Loading,
        onRefresh = { onEvent(MainUiEvent.Refresh) }
    )

    Box(
        modifier = modifier
            .fillMaxSize()
            .pullRefresh(
                state = pullToRefreshState
            )
    ) {
        when (state.loadState) {
            is LoadState.Success -> {
                SectionListScreen(
                    sectionList = state.sectionList,
                    onClickFavorite = { section, product ->
                        onEvent(MainUiEvent.ClickFavorite(section, product))
                    },
                    loadMoreItem = {
                        onEvent(MainUiEvent.LoadMore)
                    }
                )
            }

            is LoadState.Error -> {
                ErrorScreen(
                    errorMessage = state.loadState.error.message.toString()
                )
            }

            else -> {}
        }

        PullRefreshIndicator(
            refreshing = state.loadState is LoadState.Loading,
            state = pullToRefreshState,
            modifier = Modifier.align(Alignment.TopCenter)
        )
    }
}