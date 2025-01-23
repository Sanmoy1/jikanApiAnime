package com.example.jikanapianime.ui.screens.detail

import com.example.jikanapianime.data.model.AnimeData

// Represents the UI state for the detail screen, with loading status, anime details, and error message

data class DetailScreenState(
    val isLoading: Boolean = true,
    val anime: AnimeData? = null,
    val error: String? = null
)
