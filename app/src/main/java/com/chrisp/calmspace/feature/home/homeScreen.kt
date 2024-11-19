package com.chrisp.calmspace.feature.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.material.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.*
import com.chrisp.calmspace.feature.artikel.ArticleViewModel
import com.chrisp.calmspace.feature.auth.LoginScreen
import com.chrisp.calmspace.navigation.ArticleDestination
import com.chrisp.calmspace.ui.theme.Purple80

@Composable
fun DashboardScreen(
    username: String,
    navController: NavController,
    viewModel: ArticleViewModel = viewModel(),
    onNavigateToArticle: () -> Unit = {
        navController.navigate(ArticleDestination.ArticleList.route)
    }

) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        // Profile Photo Placeholder
                        Box(
                            modifier = Modifier
                                .size(40.dp)
                                .clip(CircleShape)
                                .background(Color.Gray)
                        )

                        Spacer(modifier = Modifier.width(8.dp))

                        // Greeting Text
                        Text(
                            text = buildAnnotatedString {
                                append("Halo, ")
                                withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                                    append(username)
                                }
                                append("!")
                            },
                            style = MaterialTheme.typography.h6.copy(color = Color.White),
                        )
                    }
                },
                actions = {
                    IconButton(onClick = { /* Notification action */ }) {
                        Icon(
                            imageVector = Icons.Filled.Notifications,
                            contentDescription = "Notifications",
                            tint = Color.Yellow
                        )
                    }
                },
                backgroundColor = Purple80,
                modifier = Modifier.height(100.dp),
            )
        },
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .padding(16.dp)
        ) {
            CustomButtons()
            Spacer(modifier = Modifier.height(6.dp))
            Text("Jadwal Konsultasimu", style = MaterialTheme.typography.h6)

            Spacer(modifier = Modifier.height(6.dp))
            ConsultationCard()

            Spacer(modifier = Modifier.height(16.dp))
            Text("Konsultasi Darurat", style = MaterialTheme.typography.h6)
            Spacer(modifier = Modifier.height(6.dp))
            ConsultationNowCard()

            // Artikel section with clickable modifier
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Artikel",
                style = MaterialTheme.typography.h6,
                modifier = Modifier
                    .clickable { onNavigateToArticle() }
                    .padding(vertical = 8.dp)
            )

//            LazyColumn {
//                items(viewModel.filteredArticles) { article ->
//                    ArticleCard(
//                        title = article.title,
//                        date = article.date,
//                        onClick = {
//                            navController.navigate(
//                                ArticleDestination.ArticleDetail.createRoute(article.id)
//                            )
//                        }
//                    )
//                }
//            }


            Spacer(modifier = Modifier.height(16.dp))
            Text("More personalized content and options can go here.")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    DashboardScreen(username = "Qyan", navController = rememberNavController())
}