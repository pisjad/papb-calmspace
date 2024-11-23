package com.chrisp.calmspace.navigation
import com.chrisp.calmspace.R
import androidx.annotation.DrawableRes
sealed class BottomNavItem(
    val route: String,
    val title: String,
    @DrawableRes val icon: Int
) {
    object Home : BottomNavItem(
        route = Screen.Home.route,
        title = "Beranda",
        icon = R.drawable.ic_home // Make sure this exists in drawable
    )
    object Article : BottomNavItem(
        route = Screen.Article.route,
        title = "Artikel",
        icon = R.drawable.ic_article // Make sure this exists in drawable
    )
    object Konsultasi : BottomNavItem(
        route = Screen.Konsultasi.route,
        title = "Konsultasi",
        icon = R.drawable.ic_konsultasi // Make sure this exists in drawable
    )
    object Forum : BottomNavItem(
        route = Screen.Forum.route,
        title = "Forum",
        icon = R.drawable.ic_forum // Make sure this exists in drawable
    )
    object Profile : BottomNavItem(
        route = Screen.Profile.route,
        title = "Profil",
        icon = R.drawable.ic_profile // Make sure this exists in drawable
    )

}