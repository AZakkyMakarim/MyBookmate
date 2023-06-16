package com.azakkymakarim.mybookmate.ui.screen.bookmark

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.azakkymakarim.mybookmate.R
import com.azakkymakarim.mybookmate.di.Injection
import com.azakkymakarim.mybookmate.ui.common.UiState
import com.azakkymakarim.mybookmate.ui.screen.HomeContent
import com.azakkymakarim.mybookmate.ui.screen.ViewModelFactory

@Composable
fun BookmarkScreen(
    modifier: Modifier = Modifier,
    bookmarkViewModel: BookmarkViewModel = viewModel(
        factory = ViewModelFactory(Injection.provideRepository())
    ),
    navigateToDetail: (Long) -> Unit,
) {
    Box(
        contentAlignment = Alignment.TopCenter,
        modifier = modifier
    ) {
        bookmarkViewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
            when (uiState) {
                is UiState.Loading -> {
                    bookmarkViewModel.getBookmarkBooks()
                }
                is UiState.Success -> {
                    HomeContent(
                        bookList = uiState.data,
                        modifier = Modifier,
                        navigateToDetail = navigateToDetail,
                    )
                    if (uiState.data.isEmpty())
                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier
                                .padding(16.dp)
                                .fillMaxWidth()
                                .fillMaxHeight()
                        ) {
                            Text(text = stringResource(R.string.empty_bookmark))
                        }
                }
                is UiState.Error -> {}
            }
        }
    }
}