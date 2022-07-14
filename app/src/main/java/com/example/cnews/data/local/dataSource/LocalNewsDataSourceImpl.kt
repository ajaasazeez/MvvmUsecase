package com.example.cnews.data.local.dataSource

import com.example.cnews.data.local.NewsDao
import com.example.cnews.data.local.databaseModels.NewsEntity
import com.example.cnews.data.remote.dto.NewsResponseDto
import com.example.cnews.data.remote.dto.toNewsEntity
import kotlinx.coroutines.flow.Flow

class LocalNewsDataSourceImpl(private val newsDao: NewsDao) : LocalNewsDataSource {
    override suspend fun saveNews(newsResponseDto: NewsResponseDto) {
        val newsList = newsResponseDto.response.docs.map {
            it.toNewsEntity() }
        newsDao.saveNews(news = newsList)
    }

    override fun getNewsFromLocal(): Flow<List<NewsEntity>> {
        return newsDao.getAllNews()
    }
}