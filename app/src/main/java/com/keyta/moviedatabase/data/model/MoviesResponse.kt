package com.keyta.moviedatabase.data.model

import com.google.gson.annotations.SerializedName
import com.keyta.moviedatabase.data.model.MovieResponse

data class MoviesResponse(

	@field:SerializedName("page")
	val page: Int,

	@field:SerializedName("total_pages")
	val totalPages: Int,

	@field:SerializedName("results")
	val results: List<MovieResponse>,

	@field:SerializedName("total_results")
	val totalResults: Int
)