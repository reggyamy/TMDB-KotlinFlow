package com.keyta.moviedatabase.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.keyta.moviedatabase.data.MovieRepository
import com.keyta.moviedatabase.domain.UseCase
import com.keyta.moviedatabase.utils.FakeData
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
internal class MovieViewModelTest{

    private lateinit var viewModel: MovieViewModel

    @get: Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var repository: MovieRepository

    @Mock
    private lateinit var moviesObserver: Observer<Resource<List<Movie>>>

    @Mock
    private lateinit var useCase: UseCase

    @Before
    fun setUp(){
        viewModel = MovieViewModel(useCase)
    }

    //fetch success
    @Test
    fun getMovies(page: Int) {
        val movies = MutableLiveData<Resource<List<Movie>>>()
        movies.value = Resource.success(FakeData.getMovies())

        `when`(repository.getMovies(page)).thenReturn(movies)
        val movieEntity = viewModel.getMovie(page).value
        verify(repository).getMovies(page)
        assertNotNull(movieEntity)
        assertEquals(20, movieEntity?.data?.size)

        viewModel.getMovie(page).observeForever(moviesObserver)
        verify(moviesObserver).onChanged(Resource.success(FakeData.getMovies()))
    }

}