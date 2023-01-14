package com.keyta.moviedatabase.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.io.Serializable

@Parcelize
data class Movie (
    val id: Int,
    val title : String?,
    val backdropPath: String?,
    val posterPath: String?,
    val releaseDate: String?,
    val voteAverage: Double,
    val overview: String?,
    val voteCount: Int,
    val popularity: Double
):Parcelable