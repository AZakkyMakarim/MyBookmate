package com.azakkymakarim.mybookmate.ui.screen.bookmark

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.azakkymakarim.mybookmate.data.Repository
import com.azakkymakarim.mybookmate.model.ListBook
import com.azakkymakarim.mybookmate.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class BookmarkViewModel(
    private val repository: Repository
) : ViewModel(){
    private val _uiState: MutableStateFlow<UiState<List<ListBook>>> =
        MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<List<ListBook>>>
        get() = _uiState

    fun getBookmarkBooks() {
        viewModelScope.launch {
            repository.getBookmarkBooks()
                .catch {
                    _uiState.value = UiState.Error(it.message.toString())
                }
                .collect { books ->
                    _uiState.value = UiState.Success(books)
                }
        }
    }
}