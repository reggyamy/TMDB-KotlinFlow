package com.keyta.moviedatabase.data.network

import com.keyta.moviedatabase.BuildConfig
import com.keyta.moviedatabase.data.model.MoviesResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface ApiService {

    @GET("movie/now_playing?api_key=${BuildConfig.API_KEY}")
    fun getMovies(@Query("page") page: Int): Call<MoviesResponse>

//    @GET("movie/{movie_id}")
//    fun getDetailMovie(
//        @Path("movie_id") movie_id : Int,
//        @Query("api_key") query: String
//    ): Flowable<ResultsItem>

}