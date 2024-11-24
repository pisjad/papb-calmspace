package com.chrisp.calmspace.navigation
sealed class Screen(val route: String){
    object Onboarding : Screen("onboarding")
    object Login : Screen("login")
    object Register : Screen("register")
    object Home : Screen("home")
    object Konsultasi : Screen("konsultasi")
    object Forum : Screen("forum")
    object Profile : Screen("profile")
    object Article : Screen("article")

}