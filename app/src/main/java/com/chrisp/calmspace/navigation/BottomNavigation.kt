package com.chrisp.calmspace.navigation

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.chrisp.calmspace.ui.theme.Purple60
import com.chrisp.calmspace.ui.theme.White60

@Composable
fun BottomNavigationBar(navController: NavController) {
    val navigationItems = listOf(
        BottomNavItem.Home,
        BottomNavItem.Article,
        BottomNavItem.Konsultasi,
        BottomNavItem.Forum,
        BottomNavItem.Profile,
    )

    NavigationBar(
        containerColor = Color.White,
        contentColor = Color.LightGray,
        tonalElevation = 24.dp,
        modifier = Modifier.fillMaxWidth()
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        navigationItems.forEach { item ->
            NavigationBarItem(
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = Purple60,
                    selectedTextColor = Purple60,
                    unselectedTextColor = White60,
                    unselectedIconColor = White60,
                    indicatorColor = Color.White
                ),
                icon = {
                    if (currentRoute == item.route)
                        Icon(
                            painter = painterResource(id = item.icon),
                            contentDescription = item.title,
                            tint = Purple60,
                            modifier = Modifier.size(24.dp)
                        )
                    else
                        Icon(
                            painter = painterResource(id = item.icon),
                            tint = Color.Gray,
                            contentDescription = item.title,
                            modifier = Modifier.size(24.dp)
                        )
                },
                label = {
                    Text(
                        text = item.title,
                        color = if (currentRoute == item.route) Purple60 else Color.Gray
                    )
                },
                onClick = {
                    navController.navigate(item.route) {
                        popUpTo(navController.graph.id) {
                            saveState = true
                            inclusive = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                selected = currentRoute == item.route,
                enabled = currentRoute != item.route,
            )
        }
    }
}