package com.keyta.moviedatabase.data.local

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.keyta.moviedatabase.utils.StatusResponse
import com.keyta.moviedatabase.utils.AppExecutors
import com.keyta.moviedatabase.utils.Resource
import com.keyta.moviedatabase.utils.Status.*
import com.keyta.moviedatabase.data.remote.ApiResponse

abstract class NetworkBoundResource<ResultType, RequestType>(private val appExecutor: AppExecutors){
    private val result = MediatorLiveData<Resource<ResultType>>()

    init {
        result.value = Resource.loading(null)

        @Suppress("LeakingThis")
        val dbSource = loadFromDB()

        result.addSource(dbSource){ data ->
            result.removeSource(dbSource)
            if (shouldFetch(data)){
                fetchFromNetwork(dbSource)
            }else{
                result.addSource(dbSource){newData ->
                    result.value = Resource.success(newData)
                }
            }
        }
    }

    protected open fun onFetchFailed(){}

    protected abstract fun loadFromDB(): LiveData<ResultType>

    protected abstract fun shouldFetch(data: ResultType?): Boolean

    protected abstract fun createCall(): LiveData<ApiResponse<RequestType>>

    protected abstract fun saveCallResult(data: RequestType)

    private fun fetchFromNetwork(dbSource: LiveData<ResultType>) {
        val apiResponse = createCall()

        result.addSource(dbSource) { newData ->
            result.value = Resource.loading(newData)
        }
        result.addSource(apiResponse) { response ->
            result.removeSource(apiResponse)
            result.removeSource(dbSource)
            when (response.status) {
                StatusResponse.SUCCESS ->
                    appExecutor.diskIO().execute {
                        saveCallResult(response.body)
                        appExecutor.mainThread().execute {
                            result.addSource(loadFromDB()) {
                                    newData -> result.setValue(Resource.success(newData))
                            }
                        }
                    }
                StatusResponse.EMPTY -> appExecutor.mainThread().execute {
                    result.addSource(loadFromDB()) { newData ->
                        result.value = Resource.success(newData)
                    }
                }
                StatusResponse.ERROR -> {
                    onFetchFailed()
                    result.addSource(dbSource) { newData ->
                        result.value = Resource.error(response.message, newData)
                    }
                }
            }
        }
    }
    fun asLiveData(): LiveData<Resource<ResultType>> = result
}