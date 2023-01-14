package com.keyta.moviedatabase.domain

import androidx.lifecycle.LiveData
import com.keyta.moviedatabase.utils.Resource

interface IRepository {
    fun getMovies(page: Int): LiveData<Resource<List<Movie>>>
}
