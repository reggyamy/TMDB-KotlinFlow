package com.keyta.moviedatabase.data

import com.keyta.moviedatabase.data.model.MovieResponse


interface LoadListCallback {
    fun onResponse(response: List<MovieResponse>)
    fun onFailed(message: String)
}
