package com.keyta.moviedatabase.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.keyta.moviedatabase.data.local.LocalDataSource
import com.keyta.moviedatabase.data.local.NetworkBoundResource
import com.keyta.moviedatabase.data.model.MovieResponse
import com.keyta.moviedatabase.data.remote.ApiResponse
import com.keyta.moviedatabase.data.remote.RemoteDataSource
import com.keyta.moviedatabase.domain.IRepository
import com.keyta.moviedatabase.domain.Movie
import com.keyta.moviedatabase.utils.AppExecutors
import com.keyta.moviedatabase.utils.MovieMapper
import com.keyta.moviedatabase.utils.Resource

class MovieRepository private constructor(
    private val remoteRepository: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
): IRepository {

    companion object{
        @Volatile
        private var instance: MovieRepository? = null

        fun getInstance(remoteDataSource: RemoteDataSource, localDataSource: LocalDataSource, appExecutors: AppExecutors): MovieRepository =
            instance ?: synchronized(this){
                instance ?: MovieRepository(remoteDataSource
                ,localDataSource, appExecutors)
            }

    }

    override fun getMovies(page: Int): LiveData<Resource<List<Movie>>> {
        return object : NetworkBoundResource<List<Movie>, List<MovieResponse>>(appExecutors){
            override fun loadFromDB(): LiveData<List<Movie>> {
                return localDataSource.getMovies().map {
                    MovieMapper.mapEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<Movie>?): Boolean {
                return (data == null || data.isEmpty())
            }

            override fun createCall(): LiveData<ApiResponse<List<MovieResponse>>> =
                remoteRepository.getMovies(page)

            override fun saveCallResult(data: List<MovieResponse>) {
                val movies = MovieMapper.mapResponseToEntities(data)
                localDataSource.insertMovie(movies)
            }
        }.asLiveData()
    }

}