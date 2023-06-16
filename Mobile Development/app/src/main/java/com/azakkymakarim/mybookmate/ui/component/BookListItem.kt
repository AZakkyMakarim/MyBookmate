package com.azakkymakarim.mybookmate.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.azakkymakarim.mybookmate.ui.theme.MyBookmateTheme

@Composable
fun BookListItem(
    id: Long,
    title: String,
    genre: String,
    cover: String,
    navigateToDetail: (Long) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .clickable { navigateToDetail(id) }
    ) {
        AsyncImage(
            model = cover,
            contentDescription = title,
            modifier = Modifier
                .padding(8.dp)
                .size(80.dp)
        )
        Column(
            horizontalAlignment = Alignment.Start,
            modifier = Modifier
                .padding(start = 16.dp, end = 8.dp)
        ) {
            Text(
                text = title,
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                text = genre,
                fontWeight = FontWeight.Normal,
                fontSize = 16.sp,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BookListItemPreview() {
    MyBookmateTheme() {
        BookListItem(
            id = 1,
            title = "The Subtle Art Of Not Give A F*Ck",
            genre = "Self-Help",
            cover = "https://m.media-amazon.com/images/I/51mN3bY0JjL.jpg",
            navigateToDetail = {})
    }
}