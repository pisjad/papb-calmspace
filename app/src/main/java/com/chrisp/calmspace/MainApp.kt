package com.chrisp.calmspace

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.chrisp.calmspace.feature.artikel.ArticleDetailScreen
import com.chrisp.calmspace.feature.artikel.ArticleScreen
import com.chrisp.calmspace.feature.auth.RegistrationScreen
import com.chrisp.calmspace.feature.home.DashboardScreen
import com.chrisp.calmspace.feature.onboarding.OnboardingScreen
import com.chrisp.calmspace.model.ArticleModel

sealed class Screen {
    // object Onboarding : Screen()  // Commented out
    // object AuthRegister : Screen() // Commented out
    object Home : Screen()
    object ArticleList : Screen()
    data class ArticleDetail(val article: ArticleModel) : Screen()
}

sealed class BottomNavItem(
    val route: String,
    val title: String,
    val icon: Int
) {
    object Home : BottomNavItem(
        route = "home",
        title = "Beranda",
        icon = R.drawable.ic_home
    )
    object Article : BottomNavItem(
        route = "article",
        title = "Artikel",
        icon = R.drawable.ic_article
    )
    object Konsultasi : BottomNavItem(
        route = "konsultasi",
        title = "Konsultasi",
        icon = R.drawable.ic_konsultasi
    )
    object Profile : BottomNavItem(
        route = "profile",
        title = "Profil",
        icon = R.drawable.ic_profile
    )
}

@Composable
fun MainApp() {
    // Changed initial screen to Home instead of Onboarding
    var currentScreen by remember { mutableStateOf<Screen>(Screen.Home) }
    val navController = rememberNavController()

    // Removed when block and directly showing MainContent
    MainContent(
        currentScreen = currentScreen,
        onScreenChange = { newScreen -> currentScreen = newScreen },
        navController = navController
    )

    /* Commented out authentication flow
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
        else -> {
            MainContent(
                currentScreen = screen,
                onScreenChange = { newScreen -> currentScreen = newScreen },
                navController = navController
            )
        }
    }
    */
}

@Composable
private fun MainContent(
    currentScreen: Screen,
    onScreenChange: (Screen) -> Unit,
    navController: NavController
) {
    Scaffold(
        bottomBar = {
            if (currentScreen is Screen.Home ||
                currentScreen is Screen.ArticleList ||
                currentScreen is Screen.ArticleDetail) {
                BottomNavigationBar(
                    currentRoute = when (currentScreen) {
                        is Screen.Home -> "home"
                        is Screen.ArticleList -> "article"
                        else -> null
                    },
                    onNavigate = { route ->
                        when (route) {
                            "home" -> onScreenChange(Screen.Home)
                            "article" -> onScreenChange(Screen.ArticleList)
                            // Add other routes as needed
                        }
                    }
                )
            }
        }
    ) { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues)) {
            when (currentScreen) {
                is Screen.Home -> {
                    DashboardScreen(
                        username = "Qyan",
                        navController = navController
                    )
                }
                is Screen.ArticleList -> {
                    ArticleScreen(
                        onArticleClick = { article ->
                            onScreenChange(Screen.ArticleDetail(article))
                        }
                    )
                }
                is Screen.ArticleDetail -> {
                    ArticleDetailScreen(
                        article = currentScreen.article,
                        onNavigateBack = {
                            onScreenChange(Screen.ArticleList)
                        }
                    )
                }
            }
        }
    }
}

@Composable
private fun BottomNavigationBar(
    currentRoute: String?,
    onNavigate: (String) -> Unit
) {
    val items = listOf(
        BottomNavItem.Home,
        BottomNavItem.Article,
        BottomNavItem.Konsultasi,
        BottomNavItem.Profile
    )

    NavigationBar(
        containerColor = Color.White,
        tonalElevation = 8.dp
    ) {
        items.forEach { item ->
            NavigationBarItem(
                selected = currentRoute == item.route,
                onClick = { onNavigate(item.route) },
                icon = {
                    Icon(
                        painter = painterResource(id = item.icon),
                        contentDescription = item.title
                    )
                },
                label = { Text(text = item.title) },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = Color(0xFF6750A4),
                    selectedTextColor = Color(0xFF6750A4),
                    unselectedIconColor = Color.Gray,
                    unselectedTextColor = Color.Gray,
                    indicatorColor = Color.White
                )
            )
        }
    }
}