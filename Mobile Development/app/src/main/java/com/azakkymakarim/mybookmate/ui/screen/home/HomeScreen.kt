package com.azakkymakarim.mybookmate.ui.screen

import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.azakkymakarim.mybookmate.di.Injection
import com.azakkymakarim.mybookmate.model.ListBook
import com.azakkymakarim.mybookmate.ui.common.UiState
import com.azakkymakarim.mybookmate.ui.component.BookListItem
import com.azakkymakarim.mybookmate.ui.component.SearchBar
import com.azakkymakarim.mybookmate.ui.screen.home.HomeViewModel

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    homeViewModel: HomeViewModel= viewModel(
        factory = ViewModelFactory(Injection.provideRepository())
    ),
    navigateToDetail: (Long) -> Unit,
) {
    val query by homeViewModel.query
    Column(
        verticalArrangement = Arrangement.Top,
        modifier = Modifier.fillMaxSize()
    ) {

        SearchBar(
            query = query,
            onQueryChange = homeViewModel::search,
            modifier = Modifier.background(MaterialTheme.colors.primary)
        )
        Box(
            contentAlignment = Alignment.TopCenter,
            modifier = modifier
        ) {
            homeViewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
                when (uiState) {
                    is UiState.Loading -> {
                        homeViewModel.getAllBooks()
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
                                Text(text = stringResource(com.azakkymakarim.mybookmate.R.string.no_book))
                            }
                    }
                    is UiState.Error -> {}
                }
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeContent(
    bookList: List<ListBook>,
    modifier: Modifier = Modifier,
    navigateToDetail: (Long) -> Unit,
) {
    LazyColumn(
        modifier = modifier.testTag("BookList")
    ) {
        items(bookList, key = { it.book.id }) { data ->
            BookListItem(
                id = data.book.id,
                title = data.book.title,
                genre = data.book.genre,
                cover = data.book.cover,
                navigateToDetail = navigateToDetail,
                modifier = Modifier
                    .fillMaxWidth()
                    .animateItemPlacement(tween(durationMillis = 500))
            )
            Divider()
        }
    }
}
