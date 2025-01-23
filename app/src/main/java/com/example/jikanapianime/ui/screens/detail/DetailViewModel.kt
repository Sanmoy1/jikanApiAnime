package com.example.jikanapianime.ui.screens.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jikanapianime.domain.repository.AnimeRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

/**
 * ViewModel for the detail screen that displays anime details
 * @property repository Repository to fetch anime data
 */
class DetailViewModel(
    private val repository: AnimeRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(DetailScreenState())
    val uiState: StateFlow<DetailScreenState> = _uiState.asStateFlow()

    /**
     * Loads detailed information about a specific anime
     * @param animeId The unique identifier of the anime
     */
    fun loadAnimeDetails(animeId: Int) {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, error = null) }
            
            repository.getAnimeDetails(animeId)
                .onSuccess { animeData ->
                    _uiState.update { 
                        it.copy(
                            isLoading = false,
                            anime = animeData,
                            error = null
                        )
                    }
                }
                .onFailure { exception ->
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            error = exception.message ?: "Failed to load anime details"
                        )
                    }
                }
        }
    }
}
