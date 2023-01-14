package com.keyta.moviedatabase.di

import android.content.Context
import com.keyta.moviedatabase.data.network.ApiConfig
import com.keyta.moviedatabase.data.local.LocalDataSource
import com.keyta.moviedatabase.data.local.MovieDatabase
import com.keyta.moviedatabase.data.MovieRepository
import com.keyta.moviedatabase.data.remote.RemoteDataSource
import com.keyta.moviedatabase.domain.Interactor
import com.keyta.moviedatabase.domain.UseCase
import com.keyta.moviedatabase.utils.AppExecutors

object Injection {

    private fun provideRepository(context: Context): MovieRepository {

        val db = MovieDatabase.getInstance(context)

        val remoteDataSource = RemoteDataSource.getInstance(ApiConfig.provideApiService())
        val localDataSource = LocalDataSource.getInstance(db.movieDao())
        val appExecutors = AppExecutors()

        return MovieRepository.getInstance(remoteDataSource, localDataSource, appExecutors)
    }
    fun provideUseCase(context: Context): UseCase {
        val repository = provideRepository(context)
        return Interactor(repository)
    }
}