package com.example.cnews.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.cnews.presentation.newsDetails.NewsDetailScreen
import com.example.cnews.presentation.newsDetails.NewsDetailState
import com.example.cnews.presentation.newsList.NewsListScreen
import com.example.cnews.presentation.ui.theme.CNewsTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CNewsTheme {
                // A surface container using the 'background' color from the theme
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = Screen.NewsListScreen.route){
                    composable(route = Screen.NewsListScreen.route){
                        NewsListScreen(navController)
                    }
                    composable(route = Screen.NewsDetailScreen.route+"/{heading}/{details}/{image}/{date}/{authorName}",
                        ){
                        val newsDetail = NewsDetailState.NewsDetail(
                            desc = it.arguments?.getString("details")?:"",
                            heading = it.arguments?.getString("heading")?:"",
                            date = it.arguments?.getString("date")?:"",
                            imageUrl = it.arguments?.getString("image")?:"",
                            authorName = it.arguments?.getString("authorName")?:""
                        )
                        NewsDetailScreen(navController,newsDetail)
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    CNewsTheme {
        Greeting("Android")
    }
}