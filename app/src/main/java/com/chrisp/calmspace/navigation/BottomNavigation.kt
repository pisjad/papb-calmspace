package com.chrisp.calmspace.navigation
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
@Composable
fun BottomNavigation(
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
            BottomNavigationItem(
                selected = currentRoute == item.route,
                onClick = { onNavigate(item.route) },
                icon = item,
                currentRoute = currentRoute
            )
        }
    }
}
@Composable
fun RowScope.BottomNavigationItem(
    selected: Boolean,
    onClick: () -> Unit,
    icon: BottomNavItem,
    currentRoute: String?
) {
    NavigationBarItem(
        selected = selected,
        onClick = onClick,
        icon = {
            Icon(
                painter = painterResource(id = icon.icon),
                contentDescription = icon.title
            )
        },
        label = { Text(text = icon.title) },
        colors = NavigationBarItemDefaults.colors(
            selectedIconColor = Color(0xFF6750A4),
            selectedTextColor = Color(0xFF6750A4),
            unselectedIconColor = Color.Gray,
            unselectedTextColor = Color.Gray,
            indicatorColor = Color.White
        )
    )
}