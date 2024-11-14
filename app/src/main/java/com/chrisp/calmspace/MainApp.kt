// MainApp.kt
package com.chrisp.calmspace

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.chrisp.calmspace.feature.artikel.ArticleDetailScreen
import com.chrisp.calmspace.feature.artikel.ArticleScreen
import com.chrisp.calmspace.feature.auth.RegistrationScreen
import com.chrisp.calmspace.feature.home.DashboardScreen
import com.chrisp.calmspace.feature.onboarding.OnboardingScreen
import com.chrisp.calmspace.model.ArticleModel

sealed class Screen {
    object Onboarding : Screen()
    object AuthRegister : Screen()
    object Home : Screen()
    object ArticleList : Screen()
    data class ArticleDetail(val article: ArticleModel) : Screen()
}

@Composable
fun MainApp() {
    var currentScreen by remember { mutableStateOf<Screen>(Screen.Onboarding) }
    val navController = rememberNavController()

    when (val screen = currentScreen) {
        is Screen.Onboarding -> {
            OnboardingScreen(
                onFinish = {
                    currentScreen = Screen.AuthRegister
                }
            )
        }
        is Screen.AuthRegister -> {
            RegistrationScreen(
                onRegisterComplete = {
                    currentScreen = Screen.Home
                }
            )
        }
        is Screen.Home -> {
            DashboardScreen(
                username = "Qyan",
                navController = navController
            )
        }
        is Screen.ArticleList -> {
            ArticleScreen(
                onArticleClick = { article ->
                    currentScreen = Screen.ArticleDetail(article)
                }
            )
        }
        is Screen.ArticleDetail -> {
            ArticleDetailScreen(
                article = screen.article,
                onNavigateBack = {
                    currentScreen = Screen.ArticleList
                }
            )
        }
    }
}