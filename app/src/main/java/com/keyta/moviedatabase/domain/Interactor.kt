package com.keyta.moviedatabase.domain

import androidx.lifecycle.LiveData
import com.keyta.moviedatabase.data.MovieRepository
import com.keyta.moviedatabase.domain.Movie
import com.keyta.moviedatabase.domain.UseCase
import com.keyta.moviedatabase.utils.Resource

class Interactor(private val repository: MovieRepository) : UseCase {
    override fun getMovies(page: Int): LiveData<Resource<List<Movie>>> {
        return repository.getMovies(page)
    }

}
