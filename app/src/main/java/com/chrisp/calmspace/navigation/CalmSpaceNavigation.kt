package com.chrisp.calmspace.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun CalmSpaceNavigation(
    navController: NavHostController = rememberNavController()
) {
    val currentRoute = navController
        .currentBackStackEntryFlow
        .collectAsState(initial = null)
        .value?.destination?.route

    NavHost(
        navController = navController,
        startDestination = BottomNavItem.Home.route
    ) {
        composable(BottomNavItem.Home.route) {
            // TODO: Implement HomeScreen
            /* HomeScreen(
                currentRoute = currentRoute,
                onNavigate = { route ->
                    navController.navigate(route) {
                        popUpTo(navController.graph.startDestinationId)
                        launchSingleTop = true
                    }
                }
            ) */
        }

        composable(BottomNavItem.Article.route) {
            // TODO: Implement ArticleScreen
            /* ArticleScreen(
                onArticleClick = { article ->
                    navController.navigate(
                        ArticleDestination.ArticleDetail.createRoute(article.id)
                    )
                },
                currentRoute = currentRoute,
                onNavigate = { route ->
                    navController.navigate(route) {
                        popUpTo(navController.graph.startDestinationId)
                        launchSingleTop = true
                    }
                }
            ) */
        }

        composable(BottomNavItem.Konsultasi.route) {
            // TODO: Implement KonsultasiScreen
            /* KonsultasiScreen(
                currentRoute = currentRoute,
                onNavigate = { route ->
                    navController.navigate(route) {
                        popUpTo(navController.graph.startDestinationId)
                        launchSingleTop = true
                    }
                }
            ) */
        }

        composable(BottomNavItem.Profile.route) {
            // TODO: Implement ProfileScreen
            /* ProfileScreen(
                currentRoute = currentRoute,
                onNavigate = { route ->
                    navController.navigate(route) {
                        popUpTo(navController.graph.startDestinationId)
                        launchSingleTop = true
                    }
                }
            ) */
        }

        // TODO: Add article detail navigation
        // articleGraph(navController)
    }
}