package com.keyta.moviedatabase.domain

import androidx.lifecycle.LiveData
import com.keyta.moviedatabase.domain.Movie
import com.keyta.moviedatabase.utils.Resource

interface UseCase {
    fun getMovies(page: Int): LiveData<Resource<List<Movie>>>
}
