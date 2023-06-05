package com.keyta.moviedatabase.di

import android.content.Context
import com.keyta.moviedatabase.data.MovieRepository
import com.keyta.moviedatabase.data.network.ApiConfig
import com.keyta.moviedatabase.data.remote.RemoteDataSource
import com.keyta.moviedatabase.domain.Interactor
import com.keyta.moviedatabase.domain.UseCase


object Injection {

    private fun provideRepository(context: Context): MovieRepository {
        val remoteDataSource = RemoteDataSource.getInstance(ApiConfig.provideApiService())
        return MovieRepository.getInstance(remoteDataSource)
    }
    fun provideUseCase(context: Context): UseCase {
        val repository = provideRepository(context)
        return Interactor(repository)
    }
}