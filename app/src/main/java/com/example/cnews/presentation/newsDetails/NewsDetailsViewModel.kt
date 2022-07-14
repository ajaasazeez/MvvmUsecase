package com.example.cnews.presentation.newsDetails

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.cnews.domain.model.NewsModel
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NewsDetailsViewModel @Inject constructor(savedStateHandle: SavedStateHandle) :ViewModel() {

    init {
        savedStateHandle.get<String>("newsDetailArg")?.let {
            val newsModel = Gson().fromJson(it.trim(),NewsModel::class.java)
            Log.e("newsModel",newsModel.leadParagraph)
        }
    }
}