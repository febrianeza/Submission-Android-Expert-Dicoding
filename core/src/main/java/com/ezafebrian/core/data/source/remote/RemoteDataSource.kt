package com.ezafebrian.core.data.source.remote

import com.ezafebrian.core.data.source.remote.network.ApiService
import com.ezafebrian.core.data.source.remote.response.PostsResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RemoteDataSource(private val apiService: ApiService) {
    suspend fun getPosts(): Flow<ApiResponse<PostsResponse>> {
        return flow {
            try {
                val response = apiService.getPosts()
                val posts = response.data
                posts?.let {
                    if (it.isNotEmpty()) {
                        emit(ApiResponse.Success(response))
                    } else {
                        emit(ApiResponse.Empty)
                    }
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }
}