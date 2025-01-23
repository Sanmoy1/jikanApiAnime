package com.example.jikanapianime.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.jikanapianime.data.model.AnimeData
import com.example.jikanapianime.domain.repository.AnimeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

/**
 * ViewModel for the home screen that displays the list of anime
 * @property repository Repository to fetch anime data
 */
class HomeViewModel(
    private val repository: AnimeRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeScreenState())
    val uiState: StateFlow<HomeScreenState> = _uiState.asStateFlow()

    val animeList: Flow<PagingData<AnimeData>> = repository.getTopAnime()
        .cachedIn(viewModelScope)

    /**
     * Updates the loading state
     * @param isLoading Whether data is being loaded
     */
    fun setLoading(isLoading: Boolean) {
        _uiState.update { it.copy(isLoading = isLoading) }
    }

    /**
     * Updates the error state
     * @param message Error message to display
     */
    fun setError(message: String?) {
        _uiState.update { it.copy(error = message) }
    }
}
