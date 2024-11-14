package com.chrisp.calmspace.feature.konsultasi

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

data class DoctorSchedule(
    val name: String,
    val dateTime: String, // Example: "23 Oktober 2024, 09:00 WIB"
    val imageUrl: String // URL or resource for the profile image
)

class DoctorViewModel : ViewModel() {
    val doctorSchedule = mutableStateOf(
        DoctorSchedule(
            name = "Dr. Ryan Ph.D",
            dateTime = "23 Oktober 2024, 09:00 WIB",
            imageUrl = "https://example.com/profile.jpg" // Simulated image URL
        )
    )
}
