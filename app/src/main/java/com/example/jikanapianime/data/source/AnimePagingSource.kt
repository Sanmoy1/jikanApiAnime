package com.example.jikanapianime.data.source

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.jikanapianime.data.api.JikanApi
import com.example.jikanapianime.data.model.AnimeData
import retrofit2.HttpException
import java.io.IOException

/**
 * PagingSource implementation for loading paginated anime data from the Jikan API
 * @property api The Jikan API interface for making network requests
 */
class AnimePagingSource(
    private val api: JikanApi
) : PagingSource<Int, AnimeData>() {

    override fun getRefreshKey(state: PagingState<Int, AnimeData>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, AnimeData> {
        return try {
            val page = params.key ?: 1
            val response = api.getTopAnime(page)

            LoadResult.Page(
                data = response.data,
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (response.pagination.hasNextPage) page + 1 else null
            )
        } catch (e: IOException) {
            LoadResult.Error(e)
        } catch (e: HttpException) {
            LoadResult.Error(e)
        }
    }
}
