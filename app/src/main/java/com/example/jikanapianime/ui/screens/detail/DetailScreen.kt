package com.example.jikanapianime.ui.screens.detail

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.jikanapianime.data.model.AnimeData
import com.example.jikanapianime.ui.components.ErrorMessage
import com.example.jikanapianime.ui.components.LoadingIndicator
import com.example.jikanapianime.ui.components.YoutubePlayer

/**
 * Detail screen that displays detailed information about an anime
 * @param viewModel ViewModel for the detail screen
 * @param animeId ID of the anime to display
 * @param onBackClick Callback when back button is clicked
 */
@OptIn(ExperimentalMaterial3Api::class, ExperimentalGlideComposeApi::class)
@Composable
fun DetailScreen(
    viewModel: DetailViewModel,
    animeId: Int,
    onBackClick: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()

    // Load anime details when the screen is first displayed
    LaunchedEffect(animeId) {
        viewModel.loadAnimeDetails(animeId)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(uiState.anime?.title ?: "Anime Details") },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.Default.ArrowBack, "Back")
                    }
                }
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            when {
                uiState.isLoading -> {
                    LoadingIndicator()
                }
                uiState.error != null -> {
                    ErrorMessage(message = uiState.error!!)
                }
                uiState.anime != null -> {
                    AnimeDetails(anime = uiState.anime!!)
                }
            }
        }
    }
}

/**
 * Component that displays the detailed information of an anime
 * @param anime The anime data to display
 */
@OptIn(ExperimentalGlideComposeApi::class)
@Composable
private fun AnimeDetails(anime: AnimeData) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {
        // Main image or trailer
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
        ) {
            if (anime.trailer?.embedUrl != null) {
                YoutubePlayer(
                    embedUrl = anime.trailer.embedUrl,
                    modifier = Modifier.fillMaxSize()
                )
            } else {
                GlideImage(
                    model = anime.images.jpg.largeImageUrl,
                    contentDescription = "${anime.title} image",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Title
        Text(
            text = anime.title,
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Episodes
        Text(
            text = "Episodes: ${anime.episodes ?: "N/A"}",
            style = MaterialTheme.typography.bodyLarge
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Rating
        Text(
            text = "Rating: ${anime.rating?.let { "%.1f".format(it) } ?: "N/A"}",
            style = MaterialTheme.typography.bodyLarge
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Genres
        if (!anime.genres.isNullOrEmpty()) {
            Text(
                text = "Genres: ${anime.genres.joinToString { it.name }}",
                style = MaterialTheme.typography.bodyLarge
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Synopsis
        if (!anime.synopsis.isNullOrEmpty()) {
            Text(
                text = "Synopsis",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = anime.synopsis,
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}
