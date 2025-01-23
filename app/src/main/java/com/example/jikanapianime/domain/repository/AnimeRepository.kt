package com.example.jikanapianime.domain.repository

import androidx.paging.PagingData
import com.example.jikanapianime.data.model.AnimeData
import kotlinx.coroutines.flow.Flow

/**
 * Interface defining the contract for anime data operations
 */
interface AnimeRepository {
    /**
     * Gets a flow of paginated top anime data
     * @return Flow of PagingData containing AnimeData
     */
    fun getTopAnime(): Flow<PagingData<AnimeData>>

    /**
     * Gets detailed information about a specific anime
     * @param animeId The unique identifier of the anime
     * @return AnimeData containing detailed information
     */
    suspend fun getAnimeDetails(animeId: Int): Result<AnimeData>
}
