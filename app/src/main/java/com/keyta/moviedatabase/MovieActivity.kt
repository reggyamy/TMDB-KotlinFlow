package com.keyta.moviedatabase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.keyta.moviedatabase.databinding.ActivityMovieBinding
import com.keyta.moviedatabase.ui.MovieViewModel
import com.keyta.moviedatabase.ui.ViewModelFactory
import com.keyta.moviedatabase.utils.GenresSharePreferences
import kotlinx.coroutines.launch

class MovieActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMovieBinding
    private lateinit var viewModel: MovieViewModel
    private var genreId = 0
    private var genreSize = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = "Movie Database"

        val factory = ViewModelFactory.getInstance(this)
        viewModel = ViewModelProvider(this, factory)[MovieViewModel::class.java]

        val genreAdapter = GenreAdapter(true)
        viewModel.getMovieGenres().observe(this){
            if (it.body?.isNotEmpty() == true) {
                GenresSharePreferences(this).setGenres(it.body)
                genreAdapter.setData(it.body)
                binding.rvGenre.adapter = genreAdapter
                genreId = it.body[0].id!!
                genreSize = it.body.size
                getMovies(genreId)
            }
        }

        genreAdapter.genreId = { id ->
            getMovies(id)
        }
    }

    private fun getMovies(genreId: Int) {
        val mAdapter = MovieAdapter()
        binding.rvMovie.layoutManager = LinearLayoutManager(this)
        lifecycleScope.launch {
            viewModel.getMoviesByGenre(genreId).collect{
                mAdapter.submitData(it)
            }
        }
        binding.rvMovie.adapter = mAdapter
    }
}