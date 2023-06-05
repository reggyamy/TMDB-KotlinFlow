package com.keyta.moviedatabase.data.network

import com.keyta.moviedatabase.BuildConfig
import com.keyta.moviedatabase.data.model.GenresResponse
import com.keyta.moviedatabase.data.model.MoviesResponse
import com.keyta.moviedatabase.data.model.UsersReviewResponse
import com.keyta.moviedatabase.data.model.VideosResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface ApiService {

    @GET("genre/movie/list?api_key=${BuildConfig.API_KEY}")
    suspend fun getMovieGenres(): GenresResponse

    @GET("discover/movie?api_key=${BuildConfig.API_KEY}")
    suspend fun getMoviesByGenre(
        @Query("with_genres") genre: Int,
        @Query("page") page: Int
    ): MoviesResponse

    @GET("movie/{movie_id}/reviews?api_key=${BuildConfig.API_KEY}")
    suspend fun getUsersReview(
        @Path("movie_id") movieId: Int,
        @Query("page") page: Int
    ): UsersReviewResponse

    @GET("movie/{movie_id}/videos?api_key=${BuildConfig.API_KEY}")
    suspend fun getVideo(
        @Path("movie_id") movieId: Int
    ): VideosResponse
}