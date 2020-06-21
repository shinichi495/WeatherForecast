package com.line.nab.repository

import androidx.annotation.MainThread
import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.line.nab.api.ApiEmptyResponse
import com.line.nab.api.ApiErrorResponse
import com.line.nab.api.ApiResponse
import com.line.nab.api.ApiSuccessResponse
import com.line.nab.excutors.AppExcutors
import com.line.nab.model.Resource

abstract class NetworkBoundResource<ResultType, RequestType> @MainThread constructor(private val appExcutors: AppExcutors) {
    private val result = MediatorLiveData<Resource<ResultType>>()

    init {
        result.value = Resource.loading(null)
        @Suppress("leakingThis")
        val db = loadFromDb()
        result.addSource(db) {
            result.removeSource(db)
            if (needFetchDataFromNetwork(it)) {
                fetchFromNetwork(db)
            } else {
                result.addSource(db) {
                    setValue(Resource.success(it))
                }
            }
        }
    }

    private fun fetchFromNetwork(dataFromDb: LiveData<ResultType>) {
        val apiRes = createCall()
        result.addSource(dataFromDb) {
            setValue(Resource.loading(it))
        }
        result.addSource(apiRes) { res ->
            result.removeSource(apiRes)
            result.removeSource(dataFromDb)
            when (res) {
                is ApiSuccessResponse -> {
                    appExcutors.diskIO().execute {
                        saveCallResult(processResponse(res))
                        appExcutors.mainThread().execute {
                            result.addSource(loadFromDb()) {
                                setValue(Resource.success(it))
                            }
                        }
                    }
                }
                is ApiEmptyResponse -> {
                    appExcutors.mainThread().execute {
                        // reload from disk whatever we had
                        result.addSource(loadFromDb()) { newData ->
                            setValue(Resource.success(newData))
                        }
                    }
                }
                is ApiErrorResponse -> {
                    onFetchFailed()
                    result.addSource(dataFromDb) {
                        setValue(Resource.error(it, res.errorMessage))
                    }
                }
            }
        }
    }

    fun parseToLiveData () = result as LiveData<Resource<ResultType>>

    protected open fun onFetchFailed() {}

    @WorkerThread
    protected abstract fun saveCallResult(item: RequestType)

    @WorkerThread
    protected open fun processResponse(response: ApiSuccessResponse<RequestType>) = response.body

    @MainThread
    private fun setValue(newValue: Resource<ResultType>) {
        if (result.value != newValue) {
            result.value = newValue
        }
    }

    @MainThread
    protected abstract fun loadFromDb(): LiveData<ResultType>

    @MainThread
    protected abstract fun needFetchDataFromNetwork(data: ResultType): Boolean

    @MainThread
    protected abstract fun createCall(): LiveData<ApiResponse<RequestType>>

}