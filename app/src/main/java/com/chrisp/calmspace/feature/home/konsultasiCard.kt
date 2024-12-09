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
import com.chrisp.calmspace.model.DoctorModel
import com.chrisp.calmspace.ui.theme.Purple40
import com.chrisp.calmspace.ui.theme.Purple80
@Composable
fun ConsultationCard(doctor: DoctorModel, navController: NavController) {
    // Simulated dynamic data for doctor's consultation


    // Card to display doctor's schedule
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = 4.dp
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Profile Image Placeholder
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(Color.Gray) // Placeholder color for profile image
            )

            Spacer(modifier = Modifier.width(16.dp)) // Space between image and text

            // Doctor's Name and Consultation Time
            Column(modifier = Modifier.weight(1f)) {
                Text(doctor.name, style = MaterialTheme.typography.body1)
                Text(doctor.date, style = MaterialTheme.typography.body2)
            }

            // Buka Button (aligned to the right)
            Button(
                onClick = { navController.navigate("chatScreen/${doctor.name}/${doctor.date}")
                },
                modifier = Modifier.align(Alignment.CenterVertically),
                colors = ButtonDefaults.buttonColors(backgroundColor = Purple40)
            ) {
                Text("Buka")
            }
        }
    }
}