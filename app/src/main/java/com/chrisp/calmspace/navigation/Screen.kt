package com.chrisp.calmspace.navigation
sealed class Screen(val route: String){
    object Onboarding : Screen("onboarding")
    object Login : Screen("login")
    object Register : Screen("register")
    object Home : Screen("home")
    object Konsultasi : Screen("konsultasi")
    object Chat : Screen("chat")
    object Forum : Screen("forum")
    object ForumDetail : Screen("forumDetail")
    object ForumAdd : Screen("forumAdd")
    object Profile : Screen("profile")
    object Article : Screen("article")
    object ArticleDetail : Screen("articleDetail")
    object ResetPass : Screen("ResetPass")

}