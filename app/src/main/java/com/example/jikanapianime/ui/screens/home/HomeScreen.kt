package com.example.jikanapianime.ui.screens.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemKey
import com.example.jikanapianime.ui.components.AnimeListItem
import com.example.jikanapianime.ui.components.ErrorMessage
import com.example.jikanapianime.ui.components.LoadingIndicator

// Home screen displaying a paginated list of anime, using viewModel for data and onAnimeClick callback for item clicks

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    viewModel: HomeViewModel,
    onAnimeClick: (Int) -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()
    val animeList = viewModel.animeList.collectAsLazyPagingItems()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Top Anime") }
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            when {
                uiState.error != null -> {
                    ErrorMessage(message = uiState.error!!)
                }
                animeList.loadState.refresh is LoadState.Loading -> {
                    LoadingIndicator()
                }
                else -> {
                    LazyColumn {
                        items(
                            count = animeList.itemCount,
                            key = animeList.itemKey { it.id }
                        ) { index ->
                            val anime = animeList[index]
                            if (anime != null) {
                                AnimeListItem(
                                    anime = anime,
                                    onClick = { onAnimeClick(anime.id) }
                                )
                            }
                        }

                        // Handle pagination loading state
                        when (animeList.loadState.append) {
                            is LoadState.Loading -> {
                                item { LoadingIndicator() }
                            }
                            is LoadState.Error -> {
                                item {
                                    ErrorMessage(
                                        message = "Failed to load more items"
                                    )
                                }
                            }
                            else -> {}
                        }
                    }
                }
            }
        }
    }
}
