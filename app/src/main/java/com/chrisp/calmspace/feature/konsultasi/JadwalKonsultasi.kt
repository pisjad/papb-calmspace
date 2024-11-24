package com.chrisp.calmspace.feature.konsultasi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.foundation.clickable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.chrisp.calmspace.navigation.Screen
import com.chrisp.calmspace.ui.theme.ChevronLeft
import com.chrisp.calmspace.ui.theme.Purple100
import com.chrisp.calmspace.ui.theme.Purple80
import com.chrisp.calmspace.ui.theme.White100

// Main Composable Screen
@Composable
fun JadwalKonsultasi(navController: NavController) {
    val doctorViewModel: DoctorViewModel = viewModel()
    val doctorSchedule = doctorViewModel.doctorSchedule.value

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(0.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        TopAppBar(
            modifier = Modifier.height(120.dp),
            backgroundColor = Purple80, // Warna ungu sesuai dengan gambar
            elevation = 4.dp,
            title = {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 30.dp),
                    verticalAlignment = Alignment.CenterVertically // Untuk menyelaraskan secara vertikal
                ) {
                    // Ikon navigasi
                    IconButton(
                        onClick = {
                            navController.navigate(Screen.Konsultasi.route) {
                                popUpTo(Screen.JadwalKonsultasi.route) {
                                    inclusive = true
                                } }
                        },
                        modifier = Modifier.size(40.dp)
                    ) {
                        Icon(
                            imageVector = ChevronLeft,
                            contentDescription = "Chevron Left Button",
                            tint = Color.White
                        )
                    }
                    Spacer(modifier = Modifier.width(8.dp)) // Jarak antara ikon dan teks

                    // Judul dan subtitle
                    Column {
                        Text(
                            text = "Jadwal Konsultasi",
                            color = Color.White,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "Minggu ini",
                            color = Color.White.copy(alpha = 0.7f),
                            fontSize = 14.sp
                        )
                    }
                }
            }
        )
        // Doctor Card using ViewModel data
        DoctorCard(doctorSchedule)
    }
}

@Composable
fun TopBar() {

}


// Composable to display Doctor information
@Composable
fun DoctorCard(schedule: DoctorSchedule) {
    Card(
        shape = RoundedCornerShape(12.dp),
        elevation = 4.dp,
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.Start,
        ) {
            // Doctor's Profile Image
//            AsyncImage(
//                model = schedule.imageUrl,
//                contentDescription = "Profile Image",
//                contentScale = ContentScale.Crop,
//                modifier = Modifier
//                    .size(80.dp)
//                    .clip(CircleShape)
//            )

            Spacer(modifier = Modifier.height(8.dp))

            // Doctor's Name
            Text(
                text = schedule.name,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,

            )

            // Consultation Date and Time
            Text(
                text = schedule.dateTime,
                fontSize = 14.sp,
                color = Color.Gray
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = { /* Aksi untuk mulai konsultasi */ },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(backgroundColor = Purple80)
            ) {
                Text(text = "Mulai Konsultasi",
                    fontSize = 14.sp,
                    color = Color.White
                )
            }
        }
    }
}








