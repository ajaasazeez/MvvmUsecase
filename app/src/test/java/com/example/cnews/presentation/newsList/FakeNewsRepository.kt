package com.example.cnews.presentation.newsList

import android.util.Log
import com.example.cnews.common.NewsResponseGenerator
import com.example.cnews.common.Resource
import com.example.cnews.data.remote.dto.toNewsModel
import com.example.cnews.domain.model.NewsModel
import com.example.cnews.domain.repository.NewsRepository
import com.google.gson.Gson
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeNewsRepository : NewsRepository {
    override fun searchNews(query: String, page: Int): Flow<Resource<List<NewsModel>>> {
        return flow {
            emit(Resource.Success(NewsResponseGenerator.getNewsResponse().response.docs.map {
                it.toNewsModel()
            }))
            Log.e("newsModel",Gson().toJson(NewsResponseGenerator.getNewsResponse().response))
        }
    }
}