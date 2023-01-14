package com.keyta.moviedatabase.data

import com.keyta.moviedatabase.data.model.MoviesResponse
import com.keyta.moviedatabase.data.network.ApiService
import com.keyta.moviedatabase.utils.FakeData
import retrofit2.Call

//class FakeApiService : ApiService {
//    private val dummyResponse = FakeData.getMovies()
//
//    override fun getMovies(page: Int): Call<MoviesResponse> {
//        return dummyResponse
//    }
//}