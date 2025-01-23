package com.example.jikanapianime.data.model

import com.google.gson.annotations.SerializedName

/**
 * Data class representing the response from Jikan API for a single anime
 * @property data The anime data returned by the API
 */
data class SingleAnimeResponse(
    @SerializedName("data") val data: AnimeData
)
