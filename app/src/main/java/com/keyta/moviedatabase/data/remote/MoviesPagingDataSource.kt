package com.keyta.moviedatabase.data.remote

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.keyta.moviedatabase.data.model.MoviesItem
import com.keyta.moviedatabase.data.network.ApiService
import retrofit2.HttpException
import java.io.IOException

class MoviesPagingDataSource(private val apiService: ApiService, private val genreId: Int): PagingSource<Int, MoviesItem>() {

    private companion object {
        const val INITIAL_PAGE_INDEX = 1
    }

    override fun getRefreshKey(state: PagingState<Int, MoviesItem>): Int? {
        val anchorPage = state.anchorPosition?.let {
            state.closestPageToPosition(it)
        }
        return anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)

    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MoviesItem> {
        val pageIndex = params.key ?: INITIAL_PAGE_INDEX
        return try {
            val client = apiService.getMoviesByGenre(genreId, pageIndex)
            val response = client.results
            LoadResult.Page(
                data = response,
                prevKey = null,
                nextKey = if (response.isNullOrEmpty()) null else pageIndex + 1
            )

        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }
}