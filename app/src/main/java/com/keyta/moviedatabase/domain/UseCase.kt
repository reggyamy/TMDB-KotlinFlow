package com.keyta.moviedatabase.domain

import androidx.paging.PagingData
import com.keyta.moviedatabase.data.model.GenresItem
import com.keyta.moviedatabase.data.model.MoviesItem
import com.keyta.moviedatabase.data.model.ResultsItem
import com.keyta.moviedatabase.data.model.VideosItem
import com.keyta.moviedatabase.data.remote.ApiResponse
import kotlinx.coroutines.flow.Flow

interface UseCase {
    fun getMovieGenres(): Flow<ApiResponse<List<GenresItem>>>
    suspend fun getMoviesByGenre(genreId: Int): Flow<PagingData<MoviesItem>>
    fun getReviews(movieId: Int): Flow<PagingData<ResultsItem>>
    fun getVideos(movieId: Int): Flow<ApiResponse<List<VideosItem>>>
}
