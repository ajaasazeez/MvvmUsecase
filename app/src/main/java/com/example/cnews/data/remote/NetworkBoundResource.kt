package com.example.cnews.data.remote

import androidx.annotation.MainThread
import androidx.annotation.WorkerThread
import com.example.cnews.common.Resource
import kotlinx.coroutines.flow.*
import retrofit2.Response

/**
 * A repository which provides resource from local database as well as remote end point.
 *
 * [RESULT] represents the type for database.
 * [REQUEST] represents the type for network.
 */
abstract class NetworkBoundResource<RESULT, REQUEST> {

    fun asFlow() = flow<Resource<RESULT>> {

        // Emit Database content first
        emit(Resource.Success(fetchFromLocal().first()))

        // Fetch latest data from remote
        val apiResponse = fetchFromRemote()
        val responseBody = apiResponse.body()
        if (apiResponse.isSuccessful && responseBody != null) {
            saveRemoteData(responseBody)
        } else {
            emit(Resource.Error(apiResponse.message()))
        }
        emitAll(
            fetchFromLocal().map {
                Resource.Success(it)
            }
        )
    }

    @MainThread
    protected abstract fun fetchFromLocal(): Flow<RESULT>

    @MainThread
    protected abstract suspend fun fetchFromRemote(): Response<REQUEST>

    @WorkerThread
    protected abstract suspend fun saveRemoteData(response: REQUEST)
}