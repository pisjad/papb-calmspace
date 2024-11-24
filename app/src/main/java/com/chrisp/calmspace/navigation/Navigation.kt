package com.chrisp.calmspace.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.chrisp.calmspace.feature.artikel.ArticleDetailScreen
import com.chrisp.calmspace.feature.artikel.ArticleScreen
import com.chrisp.calmspace.feature.auth.HomeViewModel
import com.chrisp.calmspace.feature.auth.LoginScreen
import com.chrisp.calmspace.feature.auth.RegistrationScreen
import com.chrisp.calmspace.feature.forum.ForumAddScreen
import com.chrisp.calmspace.feature.forum.ForumDetailScreen
import com.chrisp.calmspace.feature.forum.ForumScreen
import com.chrisp.calmspace.feature.home.DashboardScreen
import com.chrisp.calmspace.feature.konsultasi.JadwalKonsultasi
import com.chrisp.calmspace.feature.konsultasi.KonsultasiScreen
import com.chrisp.calmspace.feature.onboarding.OnboardingScreen
import com.chrisp.calmspace.feature.profile.ProfileScreen

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

        composable(route = Screen.JadwalKonsultasi.route) {
            JadwalKonsultasi(navController = navController)
        }

        composable(route = Screen.Forum.route) {
            ForumScreen(navController = navController)
        }

        composable(route = Screen.ForumAdd.route) {
            ForumAddScreen(navController = navController)
        }

        composable(route = Screen.Profile.route) {
            ProfileScreen(navController = navController)
        }

        composable(route = "${Screen.ArticleDetail.route}/{article_id}",
            arguments = listOf(
                navArgument("article_id") {
                    type = NavType.StringType
                }
            ))
        {
            val article_id = it.arguments?.getString("article_id") ?: ""
            ArticleDetailScreen(navController = navController, article_id = article_id)
        }

        composable(route = "${Screen.ForumDetail.route}/{forum_id}",
            arguments = listOf(
                navArgument("forum_id") {
                    type = NavType.StringType
                }
            ))
        {
            val forum_id = it.arguments?.getString("forum_id") ?: ""
            ForumDetailScreen(navController = navController, forum_id = forum_id)
        }
    }
}