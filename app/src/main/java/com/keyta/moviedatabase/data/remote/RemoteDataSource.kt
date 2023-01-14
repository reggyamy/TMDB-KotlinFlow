package com.keyta.moviedatabase.data.remote

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.keyta.moviedatabase.data.model.MovieResponse
import com.keyta.moviedatabase.data.model.MoviesResponse
import com.keyta.moviedatabase.data.network.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteDataSource(private val apiService: ApiService) {

    fun getMovies(page: Int): LiveData<ApiResponse<List<MovieResponse>>>{
        val resultMovies = MutableLiveData<ApiResponse<List<MovieResponse>>>()

        apiService.getMovies(page).enqueue(object : Callback<MoviesResponse> {
            override fun onResponse(call: Call<MoviesResponse>, response: Response<MoviesResponse>) {
                resultMovies.value = ApiResponse.success(response.body()?.results as List<MovieResponse>)
            }

            override fun onFailure(call: Call<MoviesResponse>, t: Throwable) {
                ApiResponse.error(t.message.toString(), null)
                Log.e("Remote Data Source", "something was failed $t")
            }
        })
        return resultMovies
    }

    companion object{
        private const val INITIAL_PAGE_INDEX = 1
        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(apiService: ApiService): RemoteDataSource =
            instance ?: synchronized(this){
                instance ?: RemoteDataSource(apiService)
            }

    }

}
