package com.chrisp.calmspace.navigation
import com.chrisp.calmspace.model.ArticleModel
sealed class Screen {
    object Onboarding : Screen()
    object AuthRegister : Screen()
    object Home : Screen()
    object ArticleList : Screen()
    data class ArticleDetail(val article: ArticleModel) : Screen()
    object Forum : Screen()
}