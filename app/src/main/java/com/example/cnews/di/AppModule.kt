package com.example.cnews.di

import com.example.cnews.common.Constants
import com.example.cnews.data.local.NewsDao
import com.example.cnews.data.local.dataSource.LocalNewsDataSource
import com.example.cnews.data.local.dataSource.LocalNewsDataSourceImpl
import com.example.cnews.data.remote.NewsApi
import com.example.cnews.data.remote.dataSource.NewsRemoteDataSource
import com.example.cnews.data.remote.dataSource.NewsRemoteDataSourceImpl
import com.example.cnews.data.repository.NewsRepositoryImpl
import com.example.cnews.domain.repository.NewsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideNewsApi(
        httpClient: OkHttpClient
    ): NewsApi{
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .client(httpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NewsApi::class.java)
    }

    @Provides
    @Singleton
    fun provideNewsRepository(remoteDataSource: NewsRemoteDataSource,localNewsDataSource: LocalNewsDataSource):NewsRepository{
        return NewsRepositoryImpl(remoteDataSource,localNewsDataSource)
    }

    @Provides
    @Singleton
    fun provideRemoteNewsDataSource(newsApi: NewsApi): NewsRemoteDataSource{
        return NewsRemoteDataSourceImpl(newsApi)
    }

    @Provides
    @Singleton
    fun provideLocalNewsDataSource(newsDao: NewsDao): LocalNewsDataSource{
        return LocalNewsDataSourceImpl(newsDao)
    }


    @Provides
    @Singleton
    fun provideHttpClient(): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        val okHttpClientBuilder = OkHttpClient.Builder().addInterceptor(interceptor)
        return okHttpClientBuilder.build()
    }
}