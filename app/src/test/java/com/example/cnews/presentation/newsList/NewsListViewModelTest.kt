package com.example.cnews.presentation.newsList

import com.example.cnews.common.Resource
import com.example.cnews.domain.model.NewsModel
import com.example.cnews.domain.usecase.GetNewsUseCase
import com.example.cnews.util.MainDispatcherRule
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class NewsListViewModelTest {

    @ExperimentalCoroutinesApi
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val getNewsUseCase: GetNewsUseCase = mockk()
    private lateinit var viewModel: NewsListViewModel

    @Before
    fun setUp() {
        viewModel = NewsListViewModel(getNewsUseCase)
    }


    @Test
    fun `getNews returns result`() {
        runTest {
            //1- Mock calls
            coEvery { getNewsUseCase.searchNews("Cryptocurrency", 0) } returns flow {
                emit(
                    Resource.Success(
                        listOf(
                            NewsModel(
                                "1",
                                "test",
                                "url",
                                "sfs",
                                "sf",
                                "fs",
                                "fs",
                                "sfs",
                                "sfs",
                                null,
                                null
                            )
                        )
                    )
                )
            }

            viewModel.getNews("Cryptocurrency")

        }
        val result = viewModel.state.value.newsList.isNotEmpty()
        assertEquals(true, result)

    }

    @Test
    fun `getNews returns empty result`() {
        runTest {
            //1- Mock calls
            coEvery { getNewsUseCase.searchNews("Cryptocurrency", 0) } returns flow {
                emit(
                    Resource.Success(
                       data = listOf<NewsModel>()
                    )
                )
            }

            viewModel.getNews("Cryptocurrency")

        }
        val result = viewModel.state.value.newsList.isNotEmpty()
        assertEquals(false, result)

    }

    @Test
    fun `getNews returns error`() {
        runTest {
            //1- Mock calls
            coEvery { getNewsUseCase.searchNews("Cryptocurrency", 0) } returns flow {
                emit(
                    Resource.Error<List<NewsModel>>(
                        message = "An unexpected error occurred"
                    )
                )
            }

            viewModel.getNews("Cryptocurrency")


        }
        val result = viewModel.state.value.error.isNotEmpty()
        assertEquals(true, result)

    }


}
