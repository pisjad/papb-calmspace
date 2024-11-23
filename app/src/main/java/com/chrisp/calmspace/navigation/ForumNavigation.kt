package com.chrisp.calmspace.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.chrisp.calmspace.feature.forum.ForumScreen
import com.chrisp.calmspace.feature.forum.ForumViewModel

@Composable
fun ForumContent() {
    val navController = rememberNavController()
    val forumViewModel: ForumViewModel = viewModel()

    ForumNavigation(navController = navController, viewModel = forumViewModel)
}

fun NavGraphBuilder.forumGraph(
    navController: NavHostController,
    viewModel: ForumViewModel
) {
    composable("forum") {
        ForumScreen(
            forumViewModel = viewModel
        )
    }
}

@Composable
fun ForumNavigation(
    navController: NavHostController,
    viewModel: ForumViewModel
) {
    NavHost(
        navController = navController,
        startDestination = "forum"
    ) {
        forumGraph(navController = navController, viewModel = viewModel)
    }
}
