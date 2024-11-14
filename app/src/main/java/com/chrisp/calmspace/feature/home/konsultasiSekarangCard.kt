package com.chrisp.calmspace.feature.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.chrisp.calmspace.ui.theme.Purple40
import com.chrisp.calmspace.ui.theme.Purple60
import com.chrisp.calmspace.ui.theme.Purple80
import com.chrisp.calmspace.ui.theme.White100

@Composable
fun ConsultationNowCard() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(0.dp),
        elevation = 4.dp,
        shape = RoundedCornerShape(12.dp), // Rounded corners for the card
    ) {
        Column(
            modifier = Modifier
                .background(Purple60) // Background color for the card
                .padding(16.dp), // Padding inside the card
            horizontalAlignment = Alignment.CenterHorizontally, // Center content horizontally
            verticalArrangement = Arrangement.Center // Center content vertically
        ) {
            Button(
                onClick = { /* Handle button click */ },
                modifier = Modifier
                    .fillMaxWidth() // You can change this to a specific width if needed
                    .padding(16.dp), // Add padding if needed
                colors = ButtonDefaults.buttonColors(backgroundColor = Purple40), // Set the background color
                shape = RoundedCornerShape(12.dp), // Rounded corners for the button
                elevation = ButtonDefaults.elevation(defaultElevation = 4.dp) // Optional: shadow for the button
            ) {
                Text(
                    text = "Konsultasi Sekarang!",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White // Set text color to white
                )
            }

            Spacer(modifier = Modifier.height(8.dp)) // Spacer between texts

            // Secondary Text (Butuh Konsultasi segera...)
            Text(
                text = "Butuh Konsultasi segera, Konsultasi sekarang!",
                fontSize = 14.sp,
                color = Color.White,
                textAlign = TextAlign.Center // Correct text alignment
            )

        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewConsultationCard() {
    ConsultationNowCard()
}