package com.chrisp.calmspace.model

data class ScheduledConsultation(
    val doctor: DoctorModel, // Gunakan model dokter yang sudah ada
    val consultationDate: String,
    val consultationTime: String
)