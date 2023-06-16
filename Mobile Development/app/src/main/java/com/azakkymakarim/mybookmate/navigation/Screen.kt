package com.azakkymakarim.mybookmate.navigation

sealed class Screen(val route: String) {
    object Login: Screen("login")
    object Home : Screen("home")
    object Bookmark : Screen("bookmark")
    object DetailBook : Screen("home/{bookId}") {
        fun createRoute(bookId: Long) = "home/$bookId"
    }
}
