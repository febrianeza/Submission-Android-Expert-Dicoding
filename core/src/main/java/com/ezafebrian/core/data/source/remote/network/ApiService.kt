package com.ezafebrian.core.data.source.remote.network

import com.ezafebrian.core.BuildConfig
import com.ezafebrian.core.data.source.remote.response.PostsResponse
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface ApiService {

    companion object {
        private const val appId = BuildConfig.APP_ID
    }

    @GET("post")
    @Headers("app-id: $appId")
    suspend fun getPosts(
        @Query("page") page: Int = 0
    ) : PostsResponse
}