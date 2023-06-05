package com.keyta.moviedatabase.data.model

import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

@Parcelize
data class GenresResponse(

	@field:SerializedName("genres")
	val genres: List<GenresItem>
) : Parcelable