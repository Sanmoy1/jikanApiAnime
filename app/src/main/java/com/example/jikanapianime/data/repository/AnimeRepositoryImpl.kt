package com.example.jikanapianime.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.jikanapianime.data.api.JikanApi
import com.example.jikanapianime.data.model.AnimeData
import com.example.jikanapianime.data.source.AnimePagingSource
import com.example.jikanapianime.domain.repository.AnimeRepository
import kotlinx.coroutines.flow.Flow

/**
 * Implementation of [AnimeRepository] that fetches data from the Jikan API
 * @property api The Jikan API interface for making network requests
 */
class AnimeRepositoryImpl(
    private val api: JikanApi
) : AnimeRepository {

    companion object {
        private const val PAGE_SIZE = 25
    }

    override fun getTopAnime(): Flow<PagingData<AnimeData>> {
        return Pager(
            config = PagingConfig(
                pageSize = PAGE_SIZE,
                enablePlaceholders = false,
                initialLoadSize = PAGE_SIZE
            ),
            pagingSourceFactory = { AnimePagingSource(api) }
        ).flow
    }

    override suspend fun getAnimeDetails(animeId: Int): Result<AnimeData> {
        return try {
            val response = api.getAnimeDetails(animeId)
            Result.success(response.data)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
