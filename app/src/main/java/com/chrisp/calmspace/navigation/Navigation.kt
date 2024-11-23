package com.chrisp.calmspace.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.chrisp.calmspace.feature.artikel.ArticleScreen
import com.chrisp.calmspace.feature.forum.ForumScreen
import com.chrisp.calmspace.feature.home.DashboardScreen
import com.chrisp.calmspace.feature.konsultasi.KonsultasiScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.Home.route) {

//        composable(route = Screen.Splash.route) {
//            SplashScreen(navController = navController)
//        }

        composable(route = Screen.Home.route) {
            DashboardScreen(navController = navController)
        }

        composable(route = Screen.Article.route) {
            ArticleScreen(navController = navController)
        }

        composable(route = Screen.Konsultasi.route) {
            KonsultasiScreen(navController = navController)
        }

        composable(route = Screen.Forum.route) {
            ForumScreen(navController = navController)
        }
    }
}