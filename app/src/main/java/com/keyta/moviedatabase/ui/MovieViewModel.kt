package com.keyta.moviedatabase.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.keyta.moviedatabase.domain.UseCase

class MovieViewModel(private  val useCase: UseCase) : ViewModel(){
    fun getMovieGenres() = useCase.getMovieGenres().asLiveData()
    suspend fun getMoviesByGenre(genreId: Int) = useCase.getMoviesByGenre(genreId)
    fun getReviews(movieId: Int) = useCase.getReviews(movieId)
    fun getVideos(movieId: Int) = useCase.getVideos(movieId).asLiveData()
}