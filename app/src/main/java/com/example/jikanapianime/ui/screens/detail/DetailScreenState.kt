package com.example.jikanapianime.ui.screens.detail

import com.example.jikanapianime.data.model.AnimeData

/**
 * Represents the UI state for the detail screen
 * @property isLoading Whether the data is being loaded
 * @property anime The anime details to display
 * @property error Error message if any error occurred
 */
data class DetailScreenState(
    val isLoading: Boolean = true,
    val anime: AnimeData? = null,
    val error: String? = null
)
