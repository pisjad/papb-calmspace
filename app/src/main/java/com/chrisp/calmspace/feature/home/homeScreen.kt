package com.chrisp.calmspace.feature.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.material.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
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
import com.chrisp.calmspace.feature.auth.HomeViewModel
import com.chrisp.calmspace.feature.auth.LoginScreen
import com.chrisp.calmspace.feature.konsultasi.DoctorCard
import com.chrisp.calmspace.feature.konsultasi.DoctorViewModel
import com.chrisp.calmspace.navigation.BottomNavigationBar
import com.chrisp.calmspace.navigation.Screen
import com.chrisp.calmspace.ui.theme.Purple80

@Composable
fun DashboardScreen(
    navController: NavController,
) {
    val viewModel: HomeViewModel = viewModel()
    val doctorViewModel: DoctorViewModel = viewModel()  // Get ViewModel instance

    val doctors = doctorViewModel.doctors


    Scaffold(
        bottomBar = {
            BottomNavigationBar(navController)
        },

        topBar = {
            TopAppBar(

                title = {

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .padding(top= 30.dp)


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
                                    append(viewModel.user.value?.name ?: "")
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
                modifier = Modifier.height(130.dp),
            )
        },
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .padding(16.dp)
        ) {
            item {
                CustomButtons(navController)
                Spacer(modifier = Modifier.height(10.dp))
                Text("Rekomendasi Jadwal Konsultasi", style = MaterialTheme.typography.h6)

                Spacer(modifier = Modifier.height(6.dp))
                if (doctors.isNotEmpty()) {
                    // Ambil dokter pertama dari daftar
                    val topDoctor = doctors.first()

                    // Tampilkan hanya satu dokter teratas
                    DoctorCard(doctor = topDoctor, navController)
                } else {
                    // Show loading state if no doctors are loaded
                    CircularProgressIndicator(modifier = Modifier.padding(16.dp))
                }

                Spacer(modifier = Modifier.height(16.dp))
                Text("Konsultasi Darurat", style = MaterialTheme.typography.h6)
                Spacer(modifier = Modifier.height(6.dp))
                ConsultationNowCard(navController)

                // Artikel section with clickable modifier
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "Artikel",
                    style = MaterialTheme.typography.h6,
                    modifier = Modifier
                        .clickable {
                            navController.navigate(Screen.Article.route) {
                                popUpTo(Screen.Home.route) {
                                    inclusive = true
                                }
                            }
                        }
                        .padding(vertical = 8.dp)
                )
            }


                items(viewModel.articles) { article ->
                    ArticleCard(
                        title = article?.title ?: "",
                        date = article?.date ?: "",
                        onClick = {
                            navController.navigate("${Screen.ArticleDetail.route}/${article?.id ?: ""}") {
                                popUpTo(Screen.Article.route) {
                                    inclusive = true
                                }
                            }
                        }
                    )
                }

        }
    }
}
