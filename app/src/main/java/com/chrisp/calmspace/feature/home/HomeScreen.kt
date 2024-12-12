package com.chrisp.calmspace.feature.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.chrisp.calmspace.R
import com.chrisp.calmspace.feature.auth.HomeViewModel
import com.chrisp.calmspace.feature.konsultasi.DoctorViewModel
import com.chrisp.calmspace.navigation.BottomNavigationBar
import com.chrisp.calmspace.navigation.Screen
import com.chrisp.calmspace.ui.theme.Purple80
import com.chrisp.calmspace.ui.theme.White60

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardScreen(
    navController: NavController,
) {
    val viewModel: HomeViewModel = viewModel()
    val doctorViewModel: DoctorViewModel = viewModel()

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
                            .padding(top = 30.dp)
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            // Profile Photo Placeholder
                            Box(contentAlignment = Alignment.BottomEnd) {
                                Icon(
                                    painter = painterResource(id = R.drawable.ic_profil), // Replace with your icon resource
                                    contentDescription = "Edit Profile Picture",
                                    tint = White60,
                                    modifier = Modifier
                                        .size(40.dp)
                                        .background(Color.White, CircleShape)
                                )
                            }

                            Spacer(modifier = Modifier.width(8.dp))

                            // Greeting Text
                            Text(
                                text = buildAnnotatedString {
                                    append("Halo, ")
                                    withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                                        append(viewModel.user.value?.name ?: "")
                                    }
                                    append(" 👋")
                                },
                                style = MaterialTheme.typography.titleLarge.copy(color = Color.White),
                            )
                        }

                        // Notification Icon Button
                        IconButton(onClick = { /* Notification action */ }) {
                            Icon(
                                imageVector = Icons.Filled.Notifications,
                                contentDescription = "Notifications",
                                tint = Color.Yellow
                            )
                        }
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Purple80),
                modifier = Modifier.height(130.dp),
            )
        },
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .padding(horizontal = 16.dp)
        ) {
            item {
                Spacer(modifier = Modifier.height(16.dp))
                CustomButtons(navController)
                Spacer(modifier = Modifier.height(10.dp))
                Text("Rekomendasi Jadwal Konsultasi", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)

                Spacer(modifier = Modifier.height(6.dp))
                if (doctors.isNotEmpty()) {
                    // Ambil dokter pertama dari daftar
                    val topDoctor = doctors.first()

                    // Tampilkan hanya satu dokter teratas
                    ConsultationCard(doctor = topDoctor, navController)
                } else {
                    // Show loading state if no doctors are loaded
                    CircularProgressIndicator(modifier = Modifier.padding(16.dp))
                }

                Spacer(modifier = Modifier.height(16.dp))
                Text("Konsultasi Darurat", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(6.dp))
                ConsultationNowCard(navController)

                // Artikel section with clickable modifier
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "Artikel",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
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