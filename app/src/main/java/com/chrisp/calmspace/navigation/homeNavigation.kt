package com.chrisp.calmspace.navigation

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.chrisp.calmspace.feature.auth.LoginScreen
import com.chrisp.calmspace.feature.home.DashboardScreen

@Composable
fun homeNavigation() {
    val navController = rememberNavController()

    // Start with login, passing username to dashboard screen
    NavHost(navController, startDestination = "login") {
        composable("login") {
            LoginScreen(onLoginSuccess = { username ->
                navController.navigate("dashboard/$username")
            })
        }
        composable("dashboard/{username}") { backStackEntry ->
            val username = backStackEntry.arguments?.getString("username") ?: "User"
            DashboardScreen(username = username, navController = navController)
        }
        composable("konsultasi") { Text("Konsultasi Screen") }
        composable("forum") { Text("Forum Screen") }
        composable("artikel") { Text("Artikel Screen") }
    }
}