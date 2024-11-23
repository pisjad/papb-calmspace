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
import com.chrisp.calmspace.feature.home.DashboardScreen
import com.chrisp.calmspace.feature.konsultasi.KonsultasiScreen
import com.chrisp.calmspace.feature.forum.ForumScreen
import com.chrisp.calmspace.feature.profile.ProfileScreen
import com.chrisp.calmspace.model.ArticleModel

sealed class Screen {
    object Home : Screen()
    object ArticleList : Screen()
    data class ArticleDetail(val article: ArticleModel) : Screen()
    object Konsultasi : Screen()
    object Forum : Screen()
    object Profile : Screen()
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
    object Forum : BottomNavItem(
        route = "forum",
        title = "Forum",
        icon = R.drawable.ic_forum
    )
    object Profile : BottomNavItem(
        route = "profile",
        title = "Profil",
        icon = R.drawable.ic_profile
    )
}

@Composable
fun MainApp() {
    var currentScreen by remember { mutableStateOf<Screen>(Screen.Home) }
    val navController = rememberNavController()

    MainContent(
        currentScreen = currentScreen,
        onScreenChange = { newScreen -> currentScreen = newScreen },
        navController = navController
    )
}

@Composable
private fun MainContent(
    currentScreen: Screen,
    onScreenChange: (Screen) -> Unit,
    navController: NavController
) {
    Scaffold(
        bottomBar = {
            // Show bottom bar for all main screens except ArticleDetail
            if (currentScreen !is Screen.ArticleDetail) {
                BottomNavigationBar(
                    currentRoute = when (currentScreen) {
                        is Screen.Home -> "home"
                        is Screen.ArticleList -> "article"
                        is Screen.Konsultasi -> "konsultasi"
                        is Screen.Forum -> "forum"
                        is Screen.Profile -> "profile"
                        else -> null
                    },
                    onNavigate = { route ->
                        when (route) {
                            "home" -> onScreenChange(Screen.Home)
                            "article" -> onScreenChange(Screen.ArticleList)
                            "konsultasi" -> onScreenChange(Screen.Konsultasi)
                            "forum" -> onScreenChange(Screen.Forum)
                            "profile" -> onScreenChange(Screen.Profile)
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
                Screen.Forum -> TODO()
                Screen.Konsultasi -> TODO()
                Screen.Profile -> TODO()
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
        BottomNavItem.Forum,
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