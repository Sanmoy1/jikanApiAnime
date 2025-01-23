package com.example.jikanapianime.ui.screens.home

/**
 * Represents the UI state for the home screen
 * @property isLoading Whether the initial data is being loaded
 * @property error Error message if any error occurred
 */
data class HomeScreenState(
    val isLoading: Boolean = false,
    val error: String? = null
)
