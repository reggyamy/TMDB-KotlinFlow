package com.keyta.moviedatabase.data.remote

import com.keyta.moviedatabase.data.model.MovieResponse
import com.keyta.moviedatabase.utils.StatusResponse

class ApiResponse<T>(val status : StatusResponse, val body: T?, val message : String?){
    companion object{
        fun <T> success(body: T?): ApiResponse<T> = ApiResponse(StatusResponse.SUCCESS, body, null)

        fun <T> empty(body: T): ApiResponse<T> = ApiResponse(StatusResponse.EMPTY, body, null)

        fun <T> error(msg: String): ApiResponse<T> = ApiResponse(StatusResponse.ERROR, null, msg)
    }
}