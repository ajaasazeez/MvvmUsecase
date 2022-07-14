package com.example.cnews.common

import com.example.cnews.CNewsApplication
import com.example.cnews.data.remote.dto.NewsResponseDto
import com.google.gson.Gson
import java.io.IOException


object NewsResponseGenerator {
    fun getNewsResponse(): NewsResponseDto {
        return Gson().fromJson(
            loadJSONFromAsset("MockNewsResponse.json"),
            NewsResponseDto::class.java
        )
    }

    private fun loadJSONFromAsset(fileName: String): String? {
        val jsonString: String = try {
            CNewsApplication().applicationContext?.assets?.open("mocks/$fileName")?.bufferedReader()
                .use { it?.readText() ?: "" }
        } catch (ex: IOException) {
            ex.printStackTrace()
            return null
        }
        return jsonString
    }
}