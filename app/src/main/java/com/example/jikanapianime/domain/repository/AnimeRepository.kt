package com.example.jikanapianime.domain.repository

import androidx.paging.PagingData
import com.example.jikanapianime.data.model.AnimeData
import kotlinx.coroutines.flow.Flow

// Interface defining the contract for anime data operations
interface AnimeRepository {
    // Gets a flow of paginated top anime data
    fun getTopAnime(): Flow<PagingData<AnimeData>>

    // Gets detailed information about a specific anime
    suspend fun getAnimeDetails(animeId: Int): Result<AnimeData>
}

