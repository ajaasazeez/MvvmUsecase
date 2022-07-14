package com.example.cnews.data.remote.dataSource

import com.example.cnews.data.remote.dto.NewsResponseDto
import retrofit2.Response

interface NewsRemoteDataSource {
     suspend fun searchNews(query:String,page:Int) : Response<NewsResponseDto>
}