package com.chrisp.calmspace

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.chrisp.calmspace.feature.artikel.ArticleDetailScreen
import com.chrisp.calmspace.feature.artikel.ArticleScreen
import com.chrisp.calmspace.feature.onboarding.OnboardingScreen
import com.chrisp.calmspace.model.ArticleModel

sealed class Screen {
    object Onboarding : Screen()
    object ArticleList : Screen()
    data class ArticleDetail(val article: ArticleModel) : Screen()
}

@Composable
fun MainApp() {
    var currentScreen by remember { mutableStateOf<Screen>(Screen.Onboarding) }

    when (val screen = currentScreen) {
        is Screen.Onboarding -> {
            OnboardingScreen(
                onFinish = {
                    currentScreen = Screen.ArticleList
                }
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