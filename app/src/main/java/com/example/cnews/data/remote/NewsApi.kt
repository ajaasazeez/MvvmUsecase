package com.example.cnews.data.remote

import com.example.cnews.data.remote.dto.NewsResponseDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {

    @GET("svc/search/v2/articlesearch.json?")
    suspend fun searchNews(
        @Query("q") query: String,
        @Query("api-key") key: String,
        @Query("page") page: Int
    ): Response<NewsResponseDto>
}