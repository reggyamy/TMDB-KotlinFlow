package com.keyta.moviedatabase.data

import androidx.paging.PagingData
import com.keyta.moviedatabase.data.model.GenresItem
import com.keyta.moviedatabase.data.model.MoviesItem
import com.keyta.moviedatabase.data.model.ResultsItem
import com.keyta.moviedatabase.data.model.VideosItem
import com.keyta.moviedatabase.data.remote.ApiResponse
import com.keyta.moviedatabase.data.remote.RemoteDataSource
import com.keyta.moviedatabase.domain.IRepository
import kotlinx.coroutines.flow.Flow

class MovieRepository private constructor(
    private val remoteDataSource: RemoteDataSource
): IRepository {

    companion object{
        @Volatile
        private var instance: MovieRepository? = null

        fun getInstance(remoteDataSource: RemoteDataSource): MovieRepository =
            instance ?: synchronized(this){
                instance ?: MovieRepository(remoteDataSource)
            }
    }

    override fun getMovieGenres(): Flow<ApiResponse<List<GenresItem>>> {
        return remoteDataSource.getGenres()
    }

    override fun getMoviesByGenre(genreId: Int): Flow<PagingData<MoviesItem>> {
        return remoteDataSource.getMoviesByGenre(genreId)
    }

    override fun getReviews(movieId: Int): Flow<PagingData<ResultsItem>> {
        return remoteDataSource.getReviews(movieId)
    }

    override fun getVideos(movieId: Int): Flow<ApiResponse<List<VideosItem>>> {
        return remoteDataSource.getVideos(movieId)
    }
}