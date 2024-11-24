package com.chrisp.calmspace.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.chrisp.calmspace.feature.artikel.ArticleScreen
import com.chrisp.calmspace.feature.auth.HomeViewModel
import com.chrisp.calmspace.feature.auth.LoginScreen
import com.chrisp.calmspace.feature.auth.RegistrationScreen
import com.chrisp.calmspace.feature.forum.ForumScreen
import com.chrisp.calmspace.feature.home.DashboardScreen
import com.chrisp.calmspace.feature.konsultasi.KonsultasiScreen
import com.chrisp.calmspace.feature.onboarding.OnboardingScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()
    val viewModel: NavViewModel = viewModel()

    NavHost(navController = navController, startDestination = if(viewModel.auth.uid != null) Screen.Home.route else Screen.Onboarding.route) {

        composable(route = Screen.Onboarding.route) {
            OnboardingScreen(navController = navController)
        }

        composable(route = Screen.Login.route) {
            LoginScreen(navController = navController)
        }

        composable(route = Screen.Register.route) {
            RegistrationScreen(navController = navController)
        }

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