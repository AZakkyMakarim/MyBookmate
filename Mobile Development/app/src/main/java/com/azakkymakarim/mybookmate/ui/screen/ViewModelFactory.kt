package com.azakkymakarim.mybookmate.ui.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.azakkymakarim.mybookmate.data.Repository
import com.azakkymakarim.mybookmate.ui.screen.bookmark.BookmarkViewModel
import com.azakkymakarim.mybookmate.ui.screen.detail.DetailViewModel
import com.azakkymakarim.mybookmate.ui.screen.home.HomeViewModel

class ViewModelFactory(private val repository: Repository) :
    ViewModelProvider.NewInstanceFactory(){
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            return HomeViewModel(repository) as T
        }
        else if (modelClass.isAssignableFrom(DetailViewModel::class.java)) {
            return DetailViewModel(repository) as T
        }
        else if (modelClass.isAssignableFrom(BookmarkViewModel::class.java)) {
            return BookmarkViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }
}