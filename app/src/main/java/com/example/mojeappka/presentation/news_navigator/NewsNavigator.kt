package com.example.mojeappka.presentation.news_navigator

import android.widget.Toast
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.mojeappka.R
import com.example.mojeappka.domain.model.Article
import com.example.mojeappka.presentation.bookmark.BookmarkScreen
import com.example.mojeappka.presentation.bookmark.BookmarkViewModel
import com.example.mojeappka.presentation.details.DetailsEvent
import com.example.mojeappka.presentation.details.DetailsScreen
import com.example.mojeappka.presentation.details.DetailsViewModel
import com.example.mojeappka.presentation.home.HomeScreen
import com.example.mojeappka.presentation.home.HomeViewModel
import com.example.mojeappka.presentation.location.LocationScreen
import com.example.mojeappka.presentation.navgraph.Route
import com.example.mojeappka.presentation.news_navigator.components.BottomNavigationItem
import com.example.mojeappka.presentation.news_navigator.components.NewsBottomNavigation
import com.example.mojeappka.presentation.search.SearchScreen
import com.example.mojeappka.presentation.search.SearchViewModel

@Composable
fun NewsNavigator() {

    val bottomNavigationItems = remember {
        listOf(
            BottomNavigationItem(icon = R.drawable.ic_home, text = "Domů"),
            BottomNavigationItem(icon = R.drawable.ic_search, text = "Hledej"),
            BottomNavigationItem(icon = R.drawable.ic_bookmark, text = "Záložky"),
            BottomNavigationItem(icon = R.drawable.ic_map, text = "Poloha"),
        )
    }

    val navController = rememberNavController()
    val backStackState = navController.currentBackStackEntryAsState().value
    var selectedItem by rememberSaveable {
        mutableStateOf(0)
    }
    selectedItem = remember(key1 = backStackState) {
        when (backStackState?.destination?.route) {
            Route.HomeScreen.route -> 0
            Route.SearchScreen.route -> 1
            Route.BookmarkScreen.route -> 2
            Route.LocationScreen.route -> 3
            else -> 0
        }
    }

    val isBottomBarVisible = remember(key1 = backStackState) {
        backStackState?.destination?.route == Route.HomeScreen.route
                || backStackState?.destination?.route == Route.SearchScreen.route
                || backStackState?.destination?.route == Route.BookmarkScreen.route
                || backStackState?.destination?.route == Route.LocationScreen.route
    }

    Scaffold(modifier = Modifier.fillMaxSize(), bottomBar = {
        if (isBottomBarVisible) {
            NewsBottomNavigation(items = bottomNavigationItems,
                selectedItem = selectedItem,
                onItemClick = { index ->
                    when (index) {
                        0 -> navigateToTab(
                            navController = navController, route = Route.HomeScreen.route
                        )

                        1 -> navigateToTab(
                            navController = navController, route = Route.SearchScreen.route
                        )

                        2 -> navigateToTab(
                            navController = navController, route = Route.BookmarkScreen.route
                        )

                        3 -> navigateToTab(
                            navController = navController, route = Route.LocationScreen.route
                        )

                    }
                })
        }

    }) {
        val bottomPadding = it.calculateBottomPadding()
        NavHost(
            navController = navController,
            startDestination = Route.HomeScreen.route,
            modifier = Modifier.padding(bottom = bottomPadding)
        ) {
            composable(route = Route.HomeScreen.route) {
                val viewModel: HomeViewModel = hiltViewModel()
                val articles = viewModel.news.collectAsLazyPagingItems()
                HomeScreen(
                    articles = articles,
                    navigateToDetails = { article ->
                        navigateToDetails(
                            navController = navController, article = article
                        )
                    })
            }
            composable(route = Route.SearchScreen.route) {
                val viewModel: SearchViewModel = hiltViewModel()
                val state = viewModel.state.value
                SearchScreen(state = state,
                    event = viewModel::onEvent,
                    navigateToDetails = { article ->
                        navigateToDetails(
                            navController = navController, article = article
                        )
                    })
            }
            composable(route = Route.DetailsScreen.route) {
                val viewModel: DetailsViewModel = hiltViewModel()
                if (viewModel.sideEffect != null) {
                    Toast.makeText(LocalContext.current, viewModel.sideEffect, Toast.LENGTH_SHORT)
                        .show()
                    viewModel.onEvent(DetailsEvent.RemoveSideEffect)
                }
                navController.previousBackStackEntry?.savedStateHandle?.get<Article?>("article")
                    ?.let { article ->
                        DetailsScreen(article = article,
                            event = viewModel::onEvent,
                            navigateUp = { navController.navigateUp() })
                    }

            }
            composable(route = Route.BookmarkScreen.route) {
                val viewModel: BookmarkViewModel = hiltViewModel()
                val state = viewModel.state.value
                BookmarkScreen(state = state, navigateToDetails = { article ->
                    navigateToDetails(navController = navController, article = article)
                })
            }
            composable(route = Route.LocationScreen.route) {
                LocationScreen(navController)
            }
        }
    }
}


private fun navigateToTab(navController: NavController, route: String) {
    navController.navigate(route) {
        navController.graph.startDestinationRoute?.let { screen_route ->
            popUpTo(screen_route) {
                saveState = true
            }
        }
        launchSingleTop = true
        restoreState = true
    }
}

private fun navigateToDetails(navController: NavController, article: Article) {
    navController.currentBackStackEntry?.savedStateHandle?.set("article", article)
    navController.navigate(
        route = Route.DetailsScreen.route
    )
}