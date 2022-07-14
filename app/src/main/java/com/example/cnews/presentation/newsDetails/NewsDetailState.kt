package com.example.cnews.presentation.newsDetails

data class NewsDetailState(
    val isLoading: Boolean = false,
    val newsList: NewsDetail = NewsDetail(),
    val error: String = ""
){
    data class NewsDetail(
        val desc:String ="",
        val heading:String ="",
        val date:String ="",
        val imageUrl:String ="",
        val authorName:String =""
    )
}
