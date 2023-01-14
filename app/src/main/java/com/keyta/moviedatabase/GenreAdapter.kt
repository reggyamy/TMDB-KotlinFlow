//package com.keyta.moviedatabase
//
//import android.annotation.SuppressLint
//import android.view.LayoutInflater
//import android.view.ViewGroup
//import androidx.recyclerview.widget.RecyclerView
//import com.keyta.moviedatabase.databinding.GenreItemBinding
//
//class GenreAdapter : RecyclerView.Adapter<GenreAdapter.ViewHolder>(){
//
//    val data = ArrayList<com.reggya.tmdb.model.Genre>()
//
//    @SuppressLint("NotifyDataSetChanged")
//    fun setData(genreIds: List<com.reggya.tmdb.model.Genre>?) {
//        if (genreIds == null) return
//        data.clear()
//        data.addAll(genreIds)
//        notifyDataSetChanged()
//    }
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
//        val binding = GenreItemBinding.inflate(LayoutInflater.from(parent.context))
//        return ViewHolder(binding)
//    }
//
//    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        val genre = data[position]
//        holder.bind(genre)
//    }
//
//    override fun getItemCount(): Int {
//        return data.size
//    }
//
//    inner class ViewHolder(private val binding: GenreItemBinding): RecyclerView.ViewHolder(binding.root) {
//        fun bind(genre: com.reggya.tmdb.model.Genre) {
////            val genres = Genre
////            val index = genres.genreIds.indexOf(genre.id)
////            binding.genre.text = Genre.genres[index]
//        }
//    }
//}
