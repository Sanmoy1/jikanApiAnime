package com.example.jikanapianime.data.model

import com.google.gson.annotations.SerializedName

// Data class representing the response from Jikan API, containing anime items and pagination information
data class AnimeResponse(
    @SerializedName("data") val data: List<AnimeData>,
    @SerializedName("pagination") val pagination: Pagination
)

// Data class representing pagination information, including whether there are more pages and current page details
data class Pagination(
    @SerializedName("has_next_page") val hasNextPage: Boolean,
    @SerializedName("current_page") val currentPage: Int,
    @SerializedName("last_visible_page") val lastVisiblePage: Int
)

// Data class representing an anime item with essential information such as title, episodes, rating, and genres
data class AnimeData(
    @SerializedName("mal_id") val id: Int,
    @SerializedName("title") val title: String,
    @SerializedName("episodes") val episodes: Int?,
    @SerializedName("score") val rating: Double?,
    @SerializedName("images") val images: Images,
    @SerializedName("synopsis") val synopsis: String?,
    @SerializedName("trailer") val trailer: Trailer?,
    @SerializedName("genres") val genres: List<Genre>?
)

// Data class representing image URLs for an anime in different formats (jpg, webp)
data class Images(
    @SerializedName("jpg") val jpg: ImageUrls,
    @SerializedName("webp") val webp: ImageUrls
)

// Data class representing different image sizes (small, large, and full-size URLs)
data class ImageUrls(
    @SerializedName("image_url") val imageUrl: String,
    @SerializedName("small_image_url") val smallImageUrl: String,
    @SerializedName("large_image_url") val largeImageUrl: String
)

// Data class representing trailer information, including YouTube ID and embed URL
data class Trailer(
    @SerializedName("youtube_id") val youtubeId: String?,
    @SerializedName("url") val url: String?,
    @SerializedName("embed_url") val embedUrl: String?
)

// Data class representing genre information for the anime, including genre name and ID
data class Genre(
    @SerializedName("mal_id") val id: Int,
    @SerializedName("name") val name: String
)
