package com.example.cnews.data.remote.dto

data class NewsResponseDto(
    val copyright: String,
    val response: SearchResponseDto,
    val status: String
)
