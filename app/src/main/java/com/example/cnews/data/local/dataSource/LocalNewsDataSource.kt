package com.example.cnews.data.local.dataSource

import com.example.cnews.data.local.databaseModels.NewsEntity
import com.example.cnews.data.remote.dto.NewsResponseDto
import kotlinx.coroutines.flow.Flow


interface LocalNewsDataSource {
    suspend fun saveNews(newsResponseDto: NewsResponseDto)
     fun getNewsFromLocal(): Flow<List<NewsEntity>>
}