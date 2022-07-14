package com.example.cnews.presentation.newsList

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Snackbar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.cnews.presentation.Screen
import com.example.cnews.presentation.newsList.components.NewsListItem
import com.example.cnews.presentation.ui.theme.PurpleGrey40
import java.net.URLEncoder
import java.nio.charset.StandardCharsets


@Composable
fun NewsListScreen(
    navController: NavController,
    viewModel: NewsListViewModel = hiltViewModel()
) {

    val state = viewModel.state.collectAsState()

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp, top = 20.dp)
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            Text(
                text = "Breaking News",
                style = MaterialTheme.typography.headlineLarge,
                color = PurpleGrey40
            )
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(state.value.newsList) { newsModel ->

                    NewsListItem(newsItem = newsModel,
                        onItemClick = { newsModel ->
                            navController.navigate(Screen.NewsDetailScreen.route+"/${newsModel.abstract}/" +
                                    "${newsModel.leadParagraph}/" +
                                    "${URLEncoder.encode(newsModel.multimedia, StandardCharsets.UTF_8.toString())}/" +
                                    "${newsModel.pubDate}/" +
                                    "${newsModel.byline?.original}")  })
                }
            }

        }
        if (state.value.isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
        if (state.value.error.isNotBlank()) {
            Snackbar(

                action = {
                    Button(onClick = {}) {
                        Text("Retry", color = Color.White  )
                    }
                },
                modifier = Modifier.padding(8.dp)
            ) { Text(text = state.value.error , color = Color.White) }
            /*Text(
                text = state.value.error,
                color = androidx.compose.material.MaterialTheme.colors.error,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(20.dp)
                    .align(Alignment.Center)
            )*/
        }

    }

}