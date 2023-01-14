package com.keyta.moviedatabase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.keyta.moviedatabase.utils.Status.*
import com.keyta.moviedatabase.databinding.ActivityMovieBinding
import com.keyta.moviedatabase.ui.MovieViewModel
import com.keyta.moviedatabase.ui.ViewModelFactory

class MovieActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMovieBinding
    private lateinit var viewModel: MovieViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = "Movie Database"

        val factory = ViewModelFactory.getInstance(this)
        viewModel = ViewModelProvider(this, factory)[MovieViewModel::class.java]

        val mAdapter = MovieAdapter()
        binding.rvMovie.layoutManager = LinearLayoutManager(this)
        var page = 1
        viewModel.getMovie(page).observe(this){
            when(it.status){
                SUCCESS -> {
                    mAdapter.setData(it.data)
                    binding.rvMovie.adapter = mAdapter
                }
                ERROR -> Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
                LOADING -> Toast.makeText(this, "Data Null", Toast.LENGTH_SHORT).show()
            }
        }
    }
}