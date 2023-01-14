package com.keyta.moviedatabase.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class MovieResponse(

	@field:SerializedName("overview")
	val overview: String,

	@field:SerializedName("title")
	val title: String,

	@field:SerializedName("poster_path")
	val posterPath: String?,

	@field:SerializedName("backdrop_path")
	val backdropPath: String?,

	@field:SerializedName("release_date")
	val releaseDate: String,

	@field:SerializedName("popularity")
	val popularity: Double,

	@field:SerializedName("vote_average")
	val voteAverage: Double,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("vote_count")
	val voteCount: Int
) : Parcelable