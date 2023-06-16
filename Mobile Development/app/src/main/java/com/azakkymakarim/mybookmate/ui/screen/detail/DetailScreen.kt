package com.azakkymakarim.mybookmate.ui.screen.detail

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.azakkymakarim.mybookmate.di.Injection
import com.azakkymakarim.mybookmate.ui.common.UiState
import com.azakkymakarim.mybookmate.ui.component.BookmarkButton
import com.azakkymakarim.mybookmate.ui.screen.ViewModelFactory

@Composable
fun DetailScreen(
    bookId: Long,
    detailViewModel: DetailViewModel = viewModel(
        factory = ViewModelFactory(
            Injection.provideRepository()
        )
    ),
    navigateBack: () -> Unit,
) {
    detailViewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
        when (uiState) {
            is UiState.Loading -> {
                detailViewModel.getBookById(bookId)
            }
            is UiState.Success -> {
                val data = uiState.data
                DetailContent(
                    data.book.title,
                    data.book.author,
                    data.book.genre,
                    data.book.desc,
                    data.book.cover,
                    data.isBookmark,
                    onBackClick = navigateBack,
                    onClick = { bookmark ->
                        detailViewModel.addToBookmark(data.book, bookmark)
                    }
                )
            }
            is UiState.Error -> {}
        }
    }
}

@Composable
fun DetailContent(
    title: String,
    author: String,
    genre: String,
    desc: String,
    cover: String,
    isBookmark: Boolean,
    onBackClick: () -> Unit,
    onClick: (bookmark: Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    var bookmark by rememberSaveable { mutableStateOf(isBookmark) }
    Column(modifier = modifier) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .weight(1f)
        ) {
            Box {
                AsyncImage(
                    model = cover,
                    contentDescription = title,
                    contentScale = ContentScale.FillHeight,
                    modifier = modifier
                        .padding(top = 36.dp)
                        .height(300.dp)
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(bottomStart = 20.dp, bottomEnd = 20.dp))
                )
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = stringResource(com.azakkymakarim.mybookmate.R.string.back),
                    modifier = Modifier
                        .padding(16.dp)
                        .clickable { onBackClick() }
                )
            }
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text(
                    text = title,
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.h5.copy(
                        fontWeight = FontWeight.ExtraBold
                    )
                )
                Text(
                    text = genre,
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.h6.copy(
                        fontWeight = FontWeight.Medium
                    ),
                )
                Text(
                    text = author,
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.h6.copy(
                        fontWeight = FontWeight.Light
                    ),
                )
                Spacer(modifier = Modifier.padding(8.dp))
                Text(
                    text = desc,
                    textAlign = TextAlign.Justify,
                    style = MaterialTheme.typography.h6.copy(
                        fontWeight = FontWeight.Normal
                    ),
                )
            }
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(4.dp)
                    .background(Color.LightGray)
            )

            BookmarkButton(
                isBookmark = bookmark,
                onClick = {
                    bookmark = !bookmark
                    onClick(bookmark)
                },
                modifier = Modifier.padding(16.dp)
            )
        }
    }
}