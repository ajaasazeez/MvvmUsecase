package com.example.cnews.domain.usecase

import com.example.cnews.common.Resource
import com.example.cnews.domain.model.NewsModel
import com.example.cnews.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetNewsUseCase @Inject constructor(private val newsRepository: NewsRepository) {
        fun searchNews(query: String, page: Int): Flow<Resource<List<NewsModel>>> =
        newsRepository.searchNews(query = query, page = page)
}