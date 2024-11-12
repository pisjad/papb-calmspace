// File: app/src/main/java/com/chrisp/calmspace/navigation/ArticleNavigation.kt

package com.chrisp.calmspace.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.chrisp.calmspace.feature.artikel.ArticleDetailScreen
import com.chrisp.calmspace.feature.artikel.ArticleScreen
import com.chrisp.calmspace.feature.artikel.ArticleViewModel

sealed class ArticleDestination(val route: String) {
    object ArticleList : ArticleDestination("article_list")
    object ArticleDetail : ArticleDestination("article_detail/{articleId}") {
        fun createRoute(articleId: Int) = "article_detail/$articleId"
    }
}

fun NavGraphBuilder.articleGraph(
    navController: NavHostController,
    viewModel: ArticleViewModel
) {
    composable(ArticleDestination.ArticleList.route) {
        ArticleScreen(
            onArticleClick = { article ->
                navController.navigate(
                    ArticleDestination.ArticleDetail.createRoute(article.id)
                )
            }
        )
    }

    composable(
        route = ArticleDestination.ArticleDetail.route,
        arguments = listOf(
            navArgument("articleId") { type = NavType.IntType }
        )
    ) { backStackEntry ->
        val articleId = backStackEntry.arguments?.getInt("articleId") ?: return@composable
        val article = viewModel.getArticleById(articleId)

        article?.let {
            ArticleDetailScreen(
                article = it,
                onNavigateBack = { navController.navigateUp() }
            )
        }
    }
}