package com.keyta.moviedatabase.data.local

import androidx.lifecycle.LiveData

class LocalDataSource private constructor(private val movieDao: MovieDao){

    fun getMovies() : LiveData<List<MovieEntity>> = movieDao.getMovies()

    fun insertMovie(movies: List<MovieEntity>) = movieDao.insertMovies(movies)

    fun getMovieById(id: String) = movieDao.getMovieById(id)

    companion object{
        private var instance : LocalDataSource? = null

        fun getInstance(movieDao: MovieDao): LocalDataSource =
            instance ?: synchronized(this){
                instance ?: LocalDataSource(movieDao)
            }

    }
}