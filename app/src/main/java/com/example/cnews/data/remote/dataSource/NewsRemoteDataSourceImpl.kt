package com.example.cnews.data.remote.dataSource

import com.example.cnews.common.Constants
import com.example.cnews.data.remote.NewsApi
import com.example.cnews.data.remote.dto.NewsResponseDto
import retrofit2.Response
import javax.inject.Inject

class NewsRemoteDataSourceImpl @Inject constructor(private val newsApi: NewsApi) :
    NewsRemoteDataSource {
    override suspend fun searchNews(query: String, page: Int): Response<NewsResponseDto> =
        newsApi.searchNews(query = query, key = Constants.API_KEY, page = page)
        /*flow {
            try {
                emit(Resource.Loading<List<NewsModel>>())
                val newsList = newsApi.searchNews(
                    query = query,
                    page = page,
                    key = Constants.API_KEY
                ).response.docs.map { doc -> doc.toNewsModel() }
                emit(Resource.Success<List<NewsModel>>(data = newsList))
            } catch (e: HttpException) {

                emit(
                    Resource.Error<List<NewsModel>>(
                        e.localizedMessage ?: "An unexpected error occurred"
                    )
                )
            } catch (e: IOException) {
                emit(Resource.Error<List<NewsModel>>("Couldn't reach server. Check your internet connection."))
            }

        }*/


}