package com.keyta.moviedatabase

import android.R
import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.keyta.moviedatabase.data.model.MoviesItem
import com.keyta.moviedatabase.databinding.ActivityDetailBinding
import com.keyta.moviedatabase.databinding.ContentDetailBinding
import com.keyta.moviedatabase.ui.MovieViewModel
import com.keyta.moviedatabase.ui.ViewModelFactory
import com.keyta.moviedatabase.utils.GenresSharePreferences
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.PlayerConstants
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat


class DetailActivity : AppCompatActivity() {
    companion object{
        const val EXTRA_MOVIE = "movie"
    }
    private lateinit var mBinding : ActivityDetailBinding
    private lateinit var binding: ContentDetailBinding
    private lateinit var viewModel: MovieViewModel
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityDetailBinding.inflate(layoutInflater)
        binding = mBinding.content
        setContentView(mBinding.root)

        supportActionBar?.title = "Detail Movie"

        val movie = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra(EXTRA_MOVIE, MoviesItem::class.java)
        } else {
            intent.getParcelableExtra(EXTRA_MOVIE)
        }

        if (movie != null) {
            val formatter = SimpleDateFormat("yyyy-MM-dd")
            val date = movie.releaseDate?.let { formatter.parse(it) }
            val desiredFormat = SimpleDateFormat("dd, MMM yyyy").format(date)
            binding.title.text = movie.title
            binding.releaseDate.text = "Release :$desiredFormat"
            binding.count.text = "Votes Count : ${movie.voteCount}"
            binding.voteAverage.text = "Vote average : ${movie.voteAverage}"
            binding.popularity.text = "Popularity : ${movie.popularity}"
            binding.overview.text = movie.overview
            Glide.with(this).load(BuildConfig.IMAGE_URL +movie.posterPath).into(binding.poster)
        }

        val reviewAdapter = ReviewsAdapter()
        viewModel = ViewModelProvider(this, ViewModelFactory.getInstance(this))[MovieViewModel::class.java]
        lifecycleScope.launch {
            viewModel.getReviews(movie?.id!!).collect{
                reviewAdapter.submitData(it)
            }

        }
        viewModel.getVideos(movie?.id!!).observe(this){
            if (it.body?.isNotEmpty() == true){
                val trailer = it.body.find { videosItem ->
                    videosItem.type == "Trailer"
                }
                lifecycle.addObserver(binding.video)
                binding.video.addYouTubePlayerListener(object :
                    AbstractYouTubePlayerListener() {
                    override fun onReady(youTubePlayer: YouTubePlayer) {
                        val videoId = trailer?.key
                        if (videoId != null){
                            youTubePlayer.loadVideo(videoId, 0f)
                        }
                    }
                })
            }
        }
        val genreAdapter = GenreAdapter(false)
        viewModel.getMovieGenres().observe(this){
            if (it.body?.isNotEmpty() == true) {
                GenresSharePreferences(this).setGenres(it.body)
                genreAdapter.setData(it.body)
                binding.rvGenre.adapter = genreAdapter
            }
        }
        binding.rvGenre.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.rvGenre.adapter = genreAdapter
        binding.rvReview.layoutManager = LinearLayoutManager(this)
        binding.rvReview.adapter = reviewAdapter
    }
}