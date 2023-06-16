package com.azakkymakarim.mybookmate

import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.azakkymakarim.mybookmate.navigation.NavigationItem
import com.azakkymakarim.mybookmate.navigation.Screen
import com.azakkymakarim.mybookmate.ui.screen.HomeScreen
import com.azakkymakarim.mybookmate.ui.screen.bookmark.BookmarkScreen
import com.azakkymakarim.mybookmate.ui.screen.detail.DetailScreen
import com.azakkymakarim.mybookmate.ui.screen.login.LoginScreen

@Composable
fun MyBookmateApp(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Scaffold(
        bottomBar = {
            if (currentRoute != Screen.DetailBook.route && currentRoute != Screen.Login.route) {
                BottomBar(navController)
            }
        },
        modifier = modifier
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Login.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.Login.route) {
                LoginScreen(navController)
            }
            composable(Screen.Home.route) {
                HomeScreen(
                    navigateToDetail = { bookId ->
                        navController.navigate(Screen.DetailBook.createRoute(bookId))
                    }
                )
            }
            composable(Screen.Bookmark.route) {
                BookmarkScreen(
                    navigateToDetail = { bookId ->
                        navController.navigate(Screen.DetailBook.createRoute(bookId))
                    }
                )
            }
            composable(
                route = Screen.DetailBook.route,
                arguments = listOf(navArgument("bookId") { type = NavType.LongType }),
            ) {
                val id = it.arguments?.getLong("bookId") ?: -1L
                DetailScreen(
                    bookId = id,
                    navigateBack = {
                        navController.navigateUp()
                    },
                )
            }
        }
    }
}

@Composable
private fun BottomBar(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    BottomNavigation(
        modifier = modifier
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        val navigationItems = listOf(
            NavigationItem(
                name = stringResource(R.string.menu_home),
                icon = R.drawable.home,
                screen = Screen.Home,
                contentDescription = "home_page"
            ),
            NavigationItem(
                name = stringResource(R.string.menu_bookmark),
                icon = R.drawable.bookmark,
                screen = Screen.Bookmark,
                contentDescription = "bookmark_page"
            ),
        )
        BottomNavigation {
            navigationItems.map { item ->
                BottomNavigationItem(
                    icon = {
                        Icon(
                            imageVector = ImageVector.vectorResource(item.icon),
                            contentDescription = item.contentDescription
                        )
                    },
                    label = { Text(item.name) },
                    selected = currentRoute == item.screen.route,
                    onClick = {
                        navController.navigate(item.screen.route) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            restoreState = true
                            launchSingleTop = true
                        }
                    }
                )
            }
        }
    }
}