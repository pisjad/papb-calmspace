package com.chrisp.calmspace.feature.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.chrisp.calmspace.R
import com.chrisp.calmspace.model.DoctorModel
import com.chrisp.calmspace.ui.theme.Purple40
import com.chrisp.calmspace.ui.theme.Purple60
import com.chrisp.calmspace.ui.theme.White20
import com.chrisp.calmspace.ui.theme.White40
import com.chrisp.calmspace.ui.theme.White60


@Composable
fun ConsultationCard(doctor: DoctorModel, navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth(), // Slight padding inside the parent box
            colors = CardDefaults.cardColors(containerColor = Purple60), // Set card background to white
            shape = RoundedCornerShape(12.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
        ) {
            Row(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Profile Image Placeholder
            Box(contentAlignment = Alignment.BottomEnd) {
                // Placeholder for profile picture, replace with actual image loading
                Icon(
                    painter = painterResource(id = R.drawable.ic_profil), // Replace with your icon resource
                    contentDescription = "Edit Profile Picture",
                    tint = White60,
                    modifier = Modifier
                        .size(40.dp)
                        .background(Color.White, CircleShape)
                )
            }

                Spacer(modifier = Modifier.width(16.dp)) // Space between image and text

                // Doctor's Name and Consultation Time
                Column(
                    modifier = Modifier
                        .weight(1f)
                ) {
                    Text(
                        text = doctor.name,
                        style = MaterialTheme.typography.bodyLarge,
                        fontWeight = FontWeight.Bold,
                        color = White40
                    )
                    Text(
                        text = doctor.date,
                        style = MaterialTheme.typography.bodyMedium,
                        color = White20
                    )
                }

                // Buka Button (aligned to the right)
                Button(
                    onClick = { navController.navigate("chatScreen/${doctor.name}/${doctor.date}") },
                    modifier = Modifier.align(Alignment.CenterVertically),
                    colors = ButtonDefaults.buttonColors(containerColor = Purple40),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text(text = "Buka", color = Color.White)
                }
            }
        }
    }
}
