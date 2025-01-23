package com.example.jikanapianime.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.jikanapianime.domain.repository.AnimeRepository
import com.example.jikanapianime.ui.screens.detail.DetailViewModel
import com.example.jikanapianime.ui.screens.home.HomeViewModel

/**
 * Factory for creating ViewModels with dependencies
 * @property repository Repository instance to be injected into ViewModels
 */
class ViewModelFactory(
    private val repository: AnimeRepository
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(HomeViewModel::class.java) -> {
                HomeViewModel(repository) as T
            }
            modelClass.isAssignableFrom(DetailViewModel::class.java) -> {
                DetailViewModel(repository) as T
            }
            else -> throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
        }
    }

    companion object {
        /**
         * Creates an instance of ViewModelFactory
         */
        fun create(): ViewModelFactory {
            val repository = NetworkModule.provideAnimeRepository()
            return ViewModelFactory(repository)
        }
    }
}
