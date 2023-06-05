package com.keyta.moviedatabase.data.model

import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

@Parcelize
data class MoviesResponse(

	@field:SerializedName("page")
	val page: Int,

	@field:SerializedName("total_pages")
	val totalPages: Int,

	@field:SerializedName("results")
	val results: List<MoviesItem>,

	@field:SerializedName("total_results")
	val totalResults: Int
) : Parcelable