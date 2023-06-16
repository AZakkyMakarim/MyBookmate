package com.azakkymakarim.mybookmate.ui.screen.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.azakkymakarim.mybookmate.data.Repository
import com.azakkymakarim.mybookmate.model.Book
import com.azakkymakarim.mybookmate.model.ListBook
import com.azakkymakarim.mybookmate.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DetailViewModel(
    private val repository: Repository
) : ViewModel(){
    private val _uiState: MutableStateFlow<UiState<ListBook>> =
        MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<ListBook>>
        get() = _uiState

    fun getBookById(bookId: Long) {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            _uiState.value = UiState.Success(repository.getBookById(bookId))
        }
    }

    fun addToBookmark(book: Book, isFavorited: Boolean) {
        viewModelScope.launch {
            repository.updateListBook(book.id, isFavorited)
        }
    }
}