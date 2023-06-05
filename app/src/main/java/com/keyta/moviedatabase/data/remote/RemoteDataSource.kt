package com.keyta.moviedatabase.data.remote

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.keyta.moviedatabase.data.model.GenresItem
import com.keyta.moviedatabase.data.model.MoviesItem
import com.keyta.moviedatabase.data.model.ResultsItem
import com.keyta.moviedatabase.data.model.VideosItem
import com.keyta.moviedatabase.data.network.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RemoteDataSource(private val apiService: ApiService) {

    fun getGenres(): Flow<ApiResponse<List<GenresItem>>>{
        return flow {
            try {
                val response = apiService.getMovieGenres().genres
                if (response.isNotEmpty()){
                    emit(ApiResponse.success(response))
                }else emit(ApiResponse.empty(response))
            }catch (e: Exception){
                emit(ApiResponse.error(msg = e.message.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }

    fun getMoviesByGenre(genreId: Int): Flow<PagingData<MoviesItem>>{
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                prefetchDistance = 1,
                initialLoadSize = 20,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                MoviesPagingDataSource(apiService, genreId)
            }
        ).flow
    }

    fun getReviews(movieId: Int): Flow<PagingData<ResultsItem>>{
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                prefetchDistance = 1,
                initialLoadSize = 20,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                ReviewsPagingDataSource(apiService, movieId)
            }
        ).flow
    }

    fun getVideos(movieId: Int): Flow<ApiResponse<List<VideosItem>>>{
        return flow {
            try {
                val response = apiService.getVideo(movieId).results
                if (response.isNotEmpty()){
                    emit(ApiResponse.success(response))
                }else emit(ApiResponse.empty(response))
            }catch (e: Exception){
                emit(ApiResponse.error(msg = e.message.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }

    companion object{
        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(apiService: ApiService): RemoteDataSource =
            instance ?: synchronized(this){
                instance ?: RemoteDataSource(apiService)
            }

    }

}
