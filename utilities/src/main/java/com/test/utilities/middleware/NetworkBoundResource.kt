package com.test.utilities.middleware


import androidx.annotation.MainThread
import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.test.utilities.Resource
import com.test.utilities.responses.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

abstract class NetworkBoundResource<ResultType, RequestType> {

    companion object {
        private const val TAG = "NetworkBoundResource"
    }


    fun asLiveData(): LiveData<Resource<ResultType>> = liveData {
        emit(Resource.Loading())
        val data = loadFromDb()
        emit(if (shouldFetch(data)) {
            when (val response = createCall()) {
                is ApiEmptyResponse -> {
                    Resource.Failure(response.message, response.code)
                }
                is ApiErrorResponse -> {
                    if (data != null) {
                        Resource.Success(data)
                    } else {
                        Resource.Failure(response.message, response.code)
                    }
                }
                is ApiSuccessResponse -> {
                    withContext(Dispatchers.IO) {
                        saveCallResult(processResponse(response))
//                        delay(2000)
                        val newData = loadFromDb()
                        if (newData != null) Resource.Success(newData)
                        else Resource.Failure(DATA_NOT_FOUND, DATA_NOT_FOUND_CODE)
                    }
                }
            }
        } else {
            if (data != null) {
                Resource.Success(data)
            } else {
                Resource.Failure(DATA_NOT_FOUND, DATA_NOT_FOUND_CODE)
            }
        })
    }

    protected abstract fun onFetchFailed()

    @WorkerThread
    protected open fun processResponse(response: ApiSuccessResponse<RequestType>) = response.body

    @WorkerThread
    protected abstract suspend fun saveCallResult(item: RequestType)

    @MainThread
    protected abstract fun shouldFetch(data: ResultType?): Boolean

    @WorkerThread
    protected abstract suspend fun loadFromDb(): ResultType?

    @MainThread
    protected abstract suspend fun createCall(): ApiResponse<RequestType>
}