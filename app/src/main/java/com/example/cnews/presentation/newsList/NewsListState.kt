package com.example.cnews.presentation.newsList

import com.example.cnews.domain.model.NewsModel

data class NewsListState(
    val isLoading: Boolean = false,
    val newsList: List<NewsModel> = emptyList(),
    val error: String = ""

)
