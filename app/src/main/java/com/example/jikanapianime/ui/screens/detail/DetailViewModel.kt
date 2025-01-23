package com.example.jikanapianime.ui.screens.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jikanapianime.domain.repository.AnimeRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

// ViewModel for the detail screen managing anime details, using repository to fetch data
class DetailViewModel(
    private val repository: AnimeRepository
) : ViewModel() {

    // StateFlow managing the UI state of the detail screen
    private val _uiState = MutableStateFlow(DetailScreenState())
    val uiState: StateFlow<DetailScreenState> = _uiState.asStateFlow()

    // Loads detailed information about a specific anime using its ID
    fun loadAnimeDetails(animeId: Int) {
        viewModelScope.launch {
            // Update state to loading before fetching data
            _uiState.update { it.copy(isLoading = true, error = null) }

            repository.getAnimeDetails(animeId)
                .onSuccess { animeData ->
                    // Update state with fetched anime data on success
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            anime = animeData,
                            error = null
                        )
                    }
                }
                .onFailure { exception ->
                    // Update state with error message on failure
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

