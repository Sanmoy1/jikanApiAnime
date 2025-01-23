package com.example.jikanapianime.data.model

import com.google.gson.annotations.SerializedName

/**
 * Data class representing the response from Jikan API
 * @property data List of anime items returned by the API
 * @property pagination Pagination information for the response
 */
data class AnimeResponse(
    @SerializedName("data") val data: List<AnimeData>,
    @SerializedName("pagination") val pagination: Pagination
)

/**
 * Data class representing pagination information
 * @property hasNextPage Boolean indicating if there are more pages
 * @property currentPage Current page number
 * @property lastVisiblePage Last available page number
 */
data class Pagination(
    @SerializedName("has_next_page") val hasNextPage: Boolean,
    @SerializedName("current_page") val currentPage: Int,
    @SerializedName("last_visible_page") val lastVisiblePage: Int
)

/**
 * Data class representing an anime item
 * Contains essential information about an anime series
 */
data class AnimeData(
    @SerializedName("mal_id") val id: Int,
    @SerializedName("title") val title: String,
    @SerializedName("episodes") val episodes: Int?,
    @SerializedName("score") val rating: Double?,
    @SerializedName("images") val images: Images,
    @SerializedName("synopsis") val synopsis: String?,
    @SerializedName("trailer") val trailer: Trailer?,
    @SerializedName("genres") val genres: List<Genre>?,
)

/**
 * Data class representing image URLs for an anime
 */
data class Images(
    @SerializedName("jpg") val jpg: ImageUrls,
    @SerializedName("webp") val webp: ImageUrls
)

/**
 * Data class representing different sizes of images
 */
data class ImageUrls(
    @SerializedName("image_url") val imageUrl: String,
    @SerializedName("small_image_url") val smallImageUrl: String,
    @SerializedName("large_image_url") val largeImageUrl: String
)

/**
 * Data class representing trailer information
 */
data class Trailer(
    @SerializedName("youtube_id") val youtubeId: String?,
    @SerializedName("url") val url: String?,
    @SerializedName("embed_url") val embedUrl: String?
)

/**
 * Data class representing genre information
 */
data class Genre(
    @SerializedName("mal_id") val id: Int,
    @SerializedName("name") val name: String
)
