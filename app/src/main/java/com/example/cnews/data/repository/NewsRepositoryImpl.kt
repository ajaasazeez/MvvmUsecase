package com.example.cnews.data.repository

import com.example.cnews.common.Resource
import com.example.cnews.data.local.dataSource.LocalNewsDataSource
import com.example.cnews.data.local.databaseModels.toNewsModel
import com.example.cnews.data.remote.NetworkBoundResource
import com.example.cnews.data.remote.dataSource.NewsRemoteDataSource
import com.example.cnews.data.remote.dto.NewsResponseDto
import com.example.cnews.domain.model.NewsModel
import com.example.cnews.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import retrofit2.Response
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val remoteDataSource: NewsRemoteDataSource,
    private val localNewsDataSource: LocalNewsDataSource
) : NewsRepository {
    override  fun searchNews(query: String, page: Int): Flow<Resource<List<NewsModel>>> /*{
        return remoteDataSource.searchNews(query = query, page = page)
    }*/ {
        return object : NetworkBoundResource<List<NewsModel>, NewsResponseDto>() {
            override fun fetchFromLocal(): Flow<List<NewsModel>> =
                localNewsDataSource.getNewsFromLocal()
                    .map { it.map { newsEntity -> newsEntity.toNewsModel() } }

            override suspend fun fetchFromRemote(): Response<NewsResponseDto> =
                remoteDataSource.searchNews(query = query, page = page)

            override suspend fun saveRemoteData(response: NewsResponseDto) =
                localNewsDataSource.saveNews(response)

        }.asFlow()
    }
}