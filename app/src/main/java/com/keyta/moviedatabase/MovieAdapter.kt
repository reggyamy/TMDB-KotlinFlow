package com.keyta.moviedatabase

import android.annotation.SuppressLint
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.keyta.moviedatabase.BuildConfig.IMAGE_URL
import com.keyta.moviedatabase.DetailActivity.Companion.EXTRA_MOVIE
import com.keyta.moviedatabase.databinding.MovieItemBinding
import com.keyta.moviedatabase.domain.Movie
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class MovieAdapter : RecyclerView.Adapter<MovieAdapter.ViewHolder>() {

    val data = ArrayList<Movie>()

    fun setData(movies: List<Movie>?){
        if (movies == null) return
        data.clear()
        data.addAll(movies)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = MovieItemBinding.inflate(LayoutInflater.from(parent.context))
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = data[position]
        holder.bind(data)
    }

    override fun getItemCount(): Int = data.size

    class ViewHolder(private val binding: MovieItemBinding): RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SimpleDateFormat")
        fun bind(data: Movie) {
            val formatter = SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)
            val date = data.releaseDate?.let { formatter.parse(it) }
            val desiredFormat = SimpleDateFormat("dd, MMM yyyy").format(date)
            Glide.with(itemView.context).load(IMAGE_URL+data.posterPath).into(binding.image)
            binding.title.text = data.title
            binding.date.text = desiredFormat

            itemView.setOnClickListener {
                val intent = Intent(itemView.context, DetailActivity::class.java)
                intent.putExtra(EXTRA_MOVIE, data)
                itemView.context.startActivity(intent)
            }

//            val genreAdapter = GenreAdapter()
//            binding.rvGenre.layoutManager = LinearLayoutManager(itemView.context)
//            binding.rvGenre.adapter = genreAdapter
//            if ((data.genreIds?.size ?: 0) > 3) data.genreIds?.slice(0..2)
//            genreAdapter.setData(data.genreIds)
        }
    }

}
