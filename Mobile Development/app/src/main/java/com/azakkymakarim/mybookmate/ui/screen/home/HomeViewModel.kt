package com.azakkymakarim.mybookmate.ui.screen.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.azakkymakarim.mybookmate.data.Repository
import com.azakkymakarim.mybookmate.model.ListBook
import com.azakkymakarim.mybookmate.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class HomeViewModel(
    private val repository: Repository
) : ViewModel() {
    private val _uiState: MutableStateFlow<UiState<List<ListBook>>> =
        MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<List<ListBook>>>
        get() = _uiState

    fun getAllBooks() {
        viewModelScope.launch {
            repository.getAllBooks()
                .catch {
                    _uiState.value = UiState.Error(it.message.toString())
                }
                .collect { books ->
                    _uiState.value = UiState.Success(books)
                }
        }
    }

    private val _query = mutableStateOf("")
    val query: State<String> get() = _query

    fun search(newQuery: String) {
        _query.value = newQuery
        viewModelScope.launch {
            repository.search(newQuery)
                .catch {
                    _uiState.value = UiState.Error(it.message.toString())
                }
                .collect { books ->
                    _uiState.value = UiState.Success(books)
                }
        }
    }
}