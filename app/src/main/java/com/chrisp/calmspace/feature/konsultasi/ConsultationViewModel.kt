package com.chrisp.calmspace.feature.konsultasi

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.chrisp.calmspace.model.DoctorModel
import androidx.compose.runtime.State


class ConsultationViewModel : ViewModel() {
    // Gunakan mutableStateOf untuk membuat state dari objek DoctorModel
    private val _selectedDoctor = mutableStateOf<DoctorModel?>(null)
    val selectedDoctor: State<DoctorModel?> get() = _selectedDoctor

    // Fungsi untuk set doctor yang dipilih
    fun setSelectedDoctor(doctor: DoctorModel) {
        _selectedDoctor.value = doctor
    }
}


