package com.example.jikanapianime.data.api

import com.example.jikanapianime.data.model.AnimeResponse
import com.example.jikanapianime.data.model.SingleAnimeResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Retrofit interface for Jikan API endpoints
 */
interface JikanApi {
    companion object {
        const val BASE_URL = "https://api.jikan.moe/v4/"
    }

    /**
     * Get top anime list with pagination
     * @param page Page number to fetch
     * @return [AnimeResponse] containing list of top anime
     */
    @GET("top/anime")
    suspend fun getTopAnime(
        @Query("page") page: Int
    ): AnimeResponse

    /**
     * Get detailed information about a specific anime
     * @param animeId The unique identifier of the anime
     * @return [SingleAnimeResponse] containing single anime details
     */
    @GET("anime/{id}")
    suspend fun getAnimeDetails(
        @Path("id") animeId: Int
    ): SingleAnimeResponse
}
