package com.example.cnews.presentation.newsDetails

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.cnews.R
import com.example.cnews.common.Constants
import com.example.cnews.common.Utils
import com.example.cnews.presentation.ui.theme.Gray20
import com.example.cnews.presentation.ui.theme.PurpleGrey40

@Composable
fun NewsDetailScreen(
    navController: NavController,
    newsDetail: NewsDetailState.NewsDetail
) {
    Screen(newsDetail, navController)

}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun Screen(newsDetail: NewsDetailState.NewsDetail, navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp, top = 20.dp, bottom = 20.dp)
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp), horizontalArrangement = Arrangement.SpaceBetween
            ) {
                RoundButton(
                    R.drawable.ic_back_arrow,
                    onItemClick = {
                        navController.popBackStack()
                    })
                RoundButton(R.drawable.ic_share, onItemClick = {})
            }
            Card(
                onClick = { }, shape = RoundedCornerShape(20.dp), modifier = Modifier
                    .height(250.dp)
                    .fillMaxWidth()
                    .padding(top = 20.dp)
            ) {
                AsyncImage(
                    placeholder = painterResource(R.drawable.placeholder),
                    contentScale = ContentScale.FillBounds,
                    contentDescription = null,
                    model = "${Constants.IMAGE_BASE_URL}${newsDetail.imageUrl}",
                    error = painterResource(id = R.drawable.placeholder),
                    modifier = Modifier
                        .align(CenterHorizontally)
                        .fillMaxWidth()

                )
            }
            Text(
                text = newsDetail.heading,
                style = MaterialTheme.typography.headlineLarge,
                color = PurpleGrey40,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = newsDetail.authorName,
                    style = MaterialTheme.typography.bodySmall,
                    color = Gray20
                )
                Text(
                    text = Utils.getFormattedDate(newsDetail.date),
                    style = MaterialTheme.typography.bodySmall,
                    color = Gray20
                )
            }
            Text(
                text = newsDetail.desc, style = MaterialTheme.typography.bodySmall, color = Gray20,
                modifier = Modifier.padding(top = 16.dp)
            )

        }

    }
}


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun RoundButton(iconId: Int, onItemClick: () -> Unit) {
    Card(
        modifier = Modifier
            .height(40.dp)
            .width(40.dp),
        shape = CircleShape,
        elevation = 20.dp,
        onClick = {
            onItemClick.invoke()
        }

    ) {
        Icon(
            painter = painterResource(id = iconId),
            contentDescription = "",
            tint = Gray20,
            modifier = Modifier.padding(4.dp)
        )
    }

}

