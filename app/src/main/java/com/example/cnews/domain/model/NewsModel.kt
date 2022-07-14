package com.example.cnews.domain.model

import com.example.cnews.data.remote.dto.SearchResponseDto

data class NewsModel(
    val id: String,
    val abstract: String,
    val webUrl: String,
    val leadParagraph: String,
    val multimedia: String?=null,
    val pubDate: String,
    val sectionName: String,
    val snippet: String,
    val source: String,
    val keywords: List<SearchResponseDto.Doc.Keyword>?=null,
    val byline: SearchResponseDto.Doc.Byline?=null,
    )
