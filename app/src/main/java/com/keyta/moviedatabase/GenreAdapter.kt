package com.keyta.moviedatabase

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.keyta.moviedatabase.data.model.GenresItem
import com.keyta.moviedatabase.databinding.GenreItemBinding

class GenreAdapter(private val clickAble : Boolean) : RecyclerView.Adapter<GenreAdapter.ViewHolder>(){

    val data = ArrayList<GenresItem>()
    var genreId: ((Int) -> Unit)? = null
    var indexSelected = 0

    @SuppressLint("NotifyDataSetChanged")
    fun setData(genreIds: List<GenresItem>?) {
        if (genreIds == null) return
        data.clear()
        data.addAll(genreIds)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = GenreItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val genre = data[position]
        holder.bind(genre, position)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    inner class ViewHolder(private val binding: GenreItemBinding): RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("NotifyDataSetChanged")
        fun bind(genre: GenresItem, position: Int) {
            binding.genre.text = genre.name
            itemView.setOnClickListener {
                indexSelected = position
                genreId?.invoke(genre.id!!)
                notifyDataSetChanged()
            }
            if (indexSelected == position && clickAble){
                binding.genre.setBackgroundResource(R.drawable.bg_border_yellow)
                binding.genre.setTextColor(ContextCompat.getColor(itemView.context, R.color.yellow))
            }else {
                binding.genre.setBackgroundResource(R.drawable.bg_border_cardview)
                binding.genre.setTextColor(ContextCompat.getColor(itemView.context, R.color.white))
            }
        }
    }
}
