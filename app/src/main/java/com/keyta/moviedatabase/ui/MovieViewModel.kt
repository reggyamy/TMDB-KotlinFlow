package com.keyta.moviedatabase.ui

import androidx.lifecycle.ViewModel
import com.keyta.moviedatabase.domain.UseCase

class MovieViewModel(private  val useCase: UseCase) : ViewModel(){

    fun getMovie(page: Int)  = useCase.getMovies(page)

}
