package com.chrisp.calmspace.feature.home

import androidx.compose.foundation.background
import androidx.compose.material.*
import androidx.compose.foundation.layout.*
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
import androidx.navigation.NavController
import androidx.navigation.compose.*
import com.chrisp.calmspace.feature.auth.LoginScreen
import com.chrisp.calmspace.ui.theme.Purple80

@Composable
fun DashboardScreen(username: String, navController: NavController) {
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
                                .size(40.dp) // Set size of the profile photo
                                .clip(CircleShape)
                                .background(Color.Gray) // Placeholder color
                        ) {
                            // Optional: You can use Image if you have an actual profile photo
                            // Image(painter = painterResource(id = R.drawable.profile), contentDescription = "Profile Photo")
                        }
                    }

                        Spacer(modifier = Modifier.width(8.dp)) // Space between the photo and the text

                        // Greeting Text
                    Text(
                        text = buildAnnotatedString {
                            append("Halo, ")
                            withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                                append(username)
                            }
                            append("!")
                        },
                        style = MaterialTheme.typography.h6.copy(color = Color.White), // Set the text color to white
                    ) // Dynamic greeting
                },
                actions = {
                    IconButton(onClick = { /* Notification action */ }) {
                        Icon(
                            imageVector = Icons.Filled.Notifications,
                            contentDescription = "Notifications",
                            tint = Color.Yellow // Set the icon color to yellow
                        )
                    }
                },
                backgroundColor = Purple80,
                modifier = Modifier.height(100.dp),// Purple color for the top bar
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
            // Konsultasi schedule card
            Spacer(modifier = Modifier.height(6.dp))

            ConsultationCard()
            // Emergency Consultation
            Spacer(modifier = Modifier.height(16.dp))
            Text("Konsultasi Darurat", style = MaterialTheme.typography.h6)
            Spacer(modifier = Modifier.height(6.dp))
            ConsultationNowCard()

            // Artikel section
            Spacer(modifier = Modifier.height(16.dp))
            Text("Artikel", style = MaterialTheme.typography.h6)


            // Additional content can go here
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