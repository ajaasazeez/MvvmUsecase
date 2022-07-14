package com.example.cnews.di

import com.example.cnews.data.local.NewsDao
import com.example.cnews.data.local.NewsDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DaoModule {

    @Provides
    @Singleton
    fun provideNewsDao(
        database: NewsDatabase
    ): NewsDao = database.newsDao()
}