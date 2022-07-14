package com.example.cnews.presentation.newsList.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.cnews.R
import com.example.cnews.common.Constants
import com.example.cnews.common.Utils
import com.example.cnews.domain.model.NewsModel
import com.example.cnews.presentation.ui.theme.Gray20
import com.example.cnews.presentation.ui.theme.PurpleGrey40


@Composable
fun NewsListItem(newsItem: NewsModel, onItemClick: (NewsModel) -> Unit) {
    Row(modifier = Modifier.fillMaxWidth().clickable { onItemClick(newsItem) }) {
        val imageUrl = "${Constants.IMAGE_BASE_URL}${newsItem.multimedia}"
        Card(
            shape = RoundedCornerShape(20.dp), modifier = Modifier
                .height(100.dp)
                .width(110.dp)
                .padding(10.dp)
        ) {
            AsyncImage(
                placeholder = painterResource(R.drawable.placeholder),
                contentScale = ContentScale.FillBounds,
                contentDescription = null,
                model = imageUrl,
                error = painterResource(id = R.drawable.placeholder)
            )
        }


        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp, end = 10.dp)
        ) {
            Text(
                text = newsItem.leadParagraph,
                overflow = TextOverflow.Ellipsis,
                maxLines = 2,
                style = MaterialTheme.typography.titleMedium,
                fontFamily = FontFamily.SansSerif,
                color = PurpleGrey40
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(modifier = Modifier.wrapContentWidth()) {
                    Icon(
                        modifier = Modifier
                            .height(15.dp)
                            .width(15.dp),
                        painter = painterResource(R.drawable.ic_calander),
                        contentDescription = "",
                        tint = Gray20
                    )
                    Text(
                        text = Utils.getFormattedDate(newsItem.pubDate),
                        style = MaterialTheme.typography.bodySmall,
                        modifier = Modifier.align(Alignment.CenterVertically),
                        color = Gray20
                    )
                }
                Row(modifier = Modifier.wrapContentWidth()) {
                    Icon(
                        modifier = Modifier
                            .height(15.dp)
                            .width(15.dp),
                        painter = painterResource(R.drawable.ic_time),
                        contentDescription = "",
                        tint = Gray20
                    )
                    Text(
                        text = "10 min read",
                        style = MaterialTheme.typography.bodySmall,
                        modifier = Modifier.align(Alignment.CenterVertically),
                        color = Gray20
                    )
                }
            }

        }
    }
}

