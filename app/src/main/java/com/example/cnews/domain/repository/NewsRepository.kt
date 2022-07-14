package com.example.cnews.domain.repository

import com.example.cnews.common.Resource
import com.example.cnews.domain.model.NewsModel
import kotlinx.coroutines.flow.Flow

interface NewsRepository {
     fun searchNews(query:String,page:Int) : Flow<Resource<List<NewsModel>>>
}