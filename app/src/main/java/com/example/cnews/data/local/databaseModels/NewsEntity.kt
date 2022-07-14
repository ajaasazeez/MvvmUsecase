package com.example.cnews.data.local.databaseModels

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.cnews.domain.model.NewsModel

@Entity
data class NewsEntity(
    @PrimaryKey var id: String,
    @ColumnInfo(name = "abstract")var abstract: String,
    @ColumnInfo(name = "webUrl")var webUrl: String,
    @ColumnInfo(name = "leadParagraph")var leadParagraph: String,
    @ColumnInfo(name = "multimedia")var multimedia: String?=null,
    @ColumnInfo(name = "pubDate")var pubDate: String,
    @ColumnInfo(name = "sectionName")var sectionName: String,
    @ColumnInfo(name = "snippet") var snippet: String,
    @ColumnInfo(name = "source")var source: String,
){
    constructor() : this("","","","","","","","","")
}

fun NewsEntity.toNewsModel(): NewsModel {
    return NewsModel(
        id = id,
        abstract = abstract,
        webUrl = webUrl,
        leadParagraph = leadParagraph,
        multimedia = multimedia,
        pubDate = pubDate,
        sectionName = sectionName,
        snippet = snippet,
        source = source
    )
}

