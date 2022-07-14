package com.example.cnews.data.remote.dto


import com.example.cnews.data.local.databaseModels.NewsEntity
import com.example.cnews.domain.model.NewsModel
import com.google.gson.annotations.SerializedName

data class SearchResponseDto(
    val docs: List<Doc>,
    val meta: Meta
) {
    data class Doc(
        val `abstract`: String,
        val byline: Byline,
        @SerializedName("document_type")
        val documentType: String,
        val headline: Headline,
        @SerializedName("_id")
        val id: String,
        val keywords: List<Keyword>,
        @SerializedName("lead_paragraph")
        val leadParagraph: String,
        val multimedia: List<Multimedia>,
        @SerializedName("news_desk")
        val newsDesk: String,
        @SerializedName("print_page")
        val printPage: String,
        @SerializedName("print_section")
        val printSection: String,
        @SerializedName("pub_date")
        val pubDate: String,
        @SerializedName("section_name")
        val sectionName: String,
        val snippet: String,
        val source: String,
        @SerializedName("type_of_material")
        val typeOfMaterial: String,
        val uri: String,
        @SerializedName("web_url")
        val webUrl: String,
        @SerializedName("word_count")
        val wordCount: Int
    ) {
        data class Byline(
            val organization: Any,
            val original: String,
            val person: List<Person>
        ) {
            data class Person(
                val firstname: String,
                val lastname: String,
                val middlename: Any,
                val organization: String,
                val qualifier: Any,
                val rank: Int,
                val role: String,
                val title: Any
            )
        }

        data class Headline(
            @SerializedName("content_kicker")
            val contentKicker: Any,
            val kicker: String,
            val main: String,
            val name: Any,
            @SerializedName("print_headline")
            val printHeadline: String,
            val seo: Any,
            val sub: Any
        )

        data class Keyword(
            val major: String,
            val name: String,
            val rank: Int,
            val value: String
        )

        data class Multimedia(
            val caption: Any,
            val credit: Any,
            @SerializedName("crop_name")
            val cropName: String,
            val height: Int,
            val legacy: Legacy,
            val rank: Int,
            val subType: String,
            val subtype: String,
            val type: String,
            val url: String,
            val width: Int
        ) {
            data class Legacy(
                val thumbnail: String,
                val thumbnailheight: Int,
                val thumbnailwidth: Int,
                val wide: String,
                val wideheight: Int,
                val widewidth: Int,
                val xlarge: String,
                val xlargeheight: Int,
                val xlargewidth: Int
            )
        }
    }

    data class Meta(
        val hits: Int,
        val offset: Int,
        val time: Int
    )
}


fun SearchResponseDto.Doc.toNewsModel(): NewsModel {
    return NewsModel(
        id = id,
        abstract = abstract,
        webUrl = webUrl,
        leadParagraph = leadParagraph,
        multimedia = multimedia[0].url,
        pubDate = pubDate,
        sectionName = sectionName,
        snippet = snippet,
        source = source,
        keywords = keywords, byline = byline
    )
}

fun SearchResponseDto.Doc.toNewsEntity(): NewsEntity =
    NewsEntity(
        id = id,
        abstract = abstract,
        webUrl = webUrl,
        leadParagraph = leadParagraph,
        multimedia = if (multimedia.isNotEmpty()) multimedia[0].url else null,
        pubDate = pubDate,
        sectionName = sectionName,
        snippet = snippet,
        source = source,

    )