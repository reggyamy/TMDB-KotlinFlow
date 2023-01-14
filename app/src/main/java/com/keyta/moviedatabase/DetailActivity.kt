package com.keyta.moviedatabase

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.keyta.moviedatabase.databinding.ActivityDetailBinding
import com.keyta.moviedatabase.domain.Movie
import java.text.SimpleDateFormat

class DetailActivity : AppCompatActivity() {
    companion object{
        const val EXTRA_MOVIE = "movie"
    }
    private lateinit var binding : ActivityDetailBinding
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = "Detail Movie"

        val movie = intent.getParcelableExtra<Movie>(EXTRA_MOVIE)

        if (movie != null) {
            val formatter = SimpleDateFormat("dd-MM-yyyy")
            val date = movie.releaseDate?.let { formatter.parse(it) }
            val desiredFormat = SimpleDateFormat("dd, MMM yyyy").format(date)
            binding.title.text = movie.title
            binding.releaseDate.text = "Release :$desiredFormat"
            binding.count.text = "Votes Count : ${movie.voteCount}"
            binding.voteAverage.text = "Vote average : ${movie.voteAverage}"
            binding.popularity.text = "Popularity : ${movie.popularity}"
            binding.overview.text = movie.overview

            Glide.with(this).load(BuildConfig.IMAGE_URL +movie.posterPath).into(binding.poster)
            Glide.with(this).load(BuildConfig.IMAGE_URL +movie.backdropPath).into(binding.backDrop)
        }

    }
}