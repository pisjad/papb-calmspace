package com.chrisp.calmspace.navigation
sealed class Screen(val route: String){
    object Home : Screen("home")
    object Konsultasi : Screen("konsultasi")
    object Forum : Screen("forum")
    object Profile : Screen("profile")
    object Article : Screen("article")

}