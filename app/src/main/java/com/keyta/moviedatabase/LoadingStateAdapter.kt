package com.keyta.moviedatabase

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.keyta.moviedatabase.databinding.ItemLoadingBinding

class LoadingStateAdapter(private val retry: () -> Unit) : LoadStateAdapter<LoadingStateAdapter.LoadingStateViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): LoadingStateViewHolder {
        val binding = ItemLoadingBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return LoadingStateViewHolder(binding, retry)
    }

    override fun onBindViewHolder(holder: LoadingStateViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    class LoadingStateViewHolder(private val binding: ItemLoadingBinding, retry: () -> Unit) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(loadState: LoadState) {
            if (loadState is LoadState.Error) {
                Toast.makeText(itemView.context, loadState.error.localizedMessage, Toast.LENGTH_SHORT).show()
            }
            binding.progressBar.isVisible = loadState is LoadState.Loading
        }
    }
}

