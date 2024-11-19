package com.chrisp.calmspace.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.chrisp.calmspace.feature.artikel.ArticleViewModel
import com.chrisp.calmspace.feature.home.DashboardScreen

@Composable
fun MainNavigation(
    navController: NavHostController,
    viewModel: ArticleViewModel
) {
    NavHost(navController = navController, startDestination = "dashboard") {
        // Dashboard screen
        composable("dashboard") {
            DashboardScreen(
                username = "Qyan",
                navController = navController,
                onNavigateToArticle = {
                    navController.navigate(ArticleDestination.ArticleList.route)
                }
            )
        }

        // Article navigation graph
        articleGraph(navController, viewModel)
    }
}
