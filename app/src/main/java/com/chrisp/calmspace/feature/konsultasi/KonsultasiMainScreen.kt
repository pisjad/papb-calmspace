package com.chrisp.calmspace.feature.konsultasi

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.chrisp.calmspace.model.DoctorModel
import com.chrisp.calmspace.navigation.BottomNavigationBar
import com.chrisp.calmspace.navigation.Screen
import com.chrisp.calmspace.ui.theme.Purple80

@Composable
fun KonsultasiScreen(navController: NavController) {
    val doctorViewModel: DoctorViewModel = viewModel()  // Get ViewModel instance

    // Make sure doctors are loaded
    val doctors = doctorViewModel.doctors

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                modifier = Modifier.height(120.dp),
                backgroundColor = Purple80, // Warna ungu sesuai dengan gambar
                elevation = 4.dp,
                title = {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 30.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
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
        },
        bottomBar = { BottomNavigationBar(navController) } // Add BottomNavigationBar here
    ) { innerPadding -> // Use innerPadding to adjust for top and bottom bars
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(innerPadding), // Apply the padding here
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Display a list of doctors
            if (doctors.isNotEmpty()) {
                LazyColumn(
                    modifier = Modifier.fillMaxSize()
                ) {
                    itemsIndexed(doctors) { index, doctor ->
                        DoctorCard(doctor = doctor, navController)
                    }
                }
            } else {
                // Show loading state if no doctors are loaded
                CircularProgressIndicator(modifier = Modifier.padding(16.dp))
            }
        }
    }
}





@Composable
fun DoctorCard(doctor: DoctorModel, navController: NavController) {
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

            // Doctor's Name
            Text(
                text = doctor.name,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
            )

            // Consultation Date and Time
            Text(
                text = doctor.date,
                fontSize = 14.sp,
                color = Color.Gray
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    navController.navigate("chatScreen/${doctor.name}/${doctor.date}")
                },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(backgroundColor = Purple80)
            ) {
                Text(
                    text = "Mulai Konsultasi",
                    fontSize = 14.sp,
                    color = Color.White
                )
            }
        }
    }
}


@Composable
fun SearchBar(query: String, onSearch: (String) -> Unit) {
    var text by remember { mutableStateOf(TextFieldValue(query)) }

    BasicTextField(
        value = text,
        onValueChange = {
            text = it
            onSearch(it.text)
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .background(Color.LightGray, MaterialTheme.shapes.small)
            .padding(8.dp)
    )
}


