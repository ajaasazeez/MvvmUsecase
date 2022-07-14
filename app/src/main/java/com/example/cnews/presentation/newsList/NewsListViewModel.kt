package com.example.cnews.presentation.newsList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cnews.common.Resource
import com.example.cnews.domain.usecase.GetNewsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsListViewModel @Inject constructor(private val getNewsUseCase: GetNewsUseCase) :
    ViewModel() {

    private val _state = MutableStateFlow(NewsListState())
    val state: StateFlow<NewsListState> = _state

    init {
        getNews("Cryptocurrency")
    }

     fun getNews(query: String) {

        viewModelScope.launch {
            getNewsUseCase.searchNews(query = query, page = 0).onEach { result ->
                when (result) {
                    is Resource.Error -> {
                        _state.update { NewsListState(error = result.message ?: "An unexpected error occurred")  }

                    }
                    is Resource.Loading -> {
                        _state.update { NewsListState(isLoading = true)  }
                    }
                    is Resource.Success -> {
                        result.data?.let {
                            if (it.isNotEmpty())
                                _state.update {  NewsListState(newsList = result.data)}

                        }
                    }
                }
            }.collect()
        }

    }
}