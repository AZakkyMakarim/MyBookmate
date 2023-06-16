package com.azakkymakarim.mybookmate.navigation

data class NavigationItem(
    val name: String,
    val icon: Int,
    val screen: Screen,
    val contentDescription: String,
)
