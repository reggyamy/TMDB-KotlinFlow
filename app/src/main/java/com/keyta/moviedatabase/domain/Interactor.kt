package com.keyta.moviedatabase.domain

import androidx.paging.PagingData
import com.keyta.moviedatabase.data.MovieRepository
import com.keyta.moviedatabase.data.model.GenresItem
import com.keyta.moviedatabase.data.model.MoviesItem
import com.keyta.moviedatabase.data.model.ResultsItem
import com.keyta.moviedatabase.data.model.VideosItem
import com.keyta.moviedatabase.data.remote.ApiResponse
import kotlinx.coroutines.flow.Flow

class Interactor(private val repository: MovieRepository) : UseCase {
    override fun getMovieGenres(): Flow<ApiResponse<List<GenresItem>>> {
        return repository.getMovieGenres()
    }

    override suspend fun getMoviesByGenre(genreId: Int): Flow<PagingData<MoviesItem>> {
        return repository.getMoviesByGenre(genreId)
    }

    override fun getReviews(movieId: Int): Flow<PagingData<ResultsItem>> {
        return repository.getReviews(movieId)
    }

    override fun getVideos(movieId: Int): Flow<ApiResponse<List<VideosItem>>> {
        return repository.getVideos(movieId)
    }
}
