package com.chrisp.calmspace.feature.konsultasi

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.chrisp.calmspace.feature.data.Repository
import com.chrisp.calmspace.model.DoctorModel

class DoctorViewModel : ViewModel() {
    val repository = Repository()
    val doctors = mutableStateListOf<DoctorModel>() // Initialize as empty list

    init {
        repository.getAllDoctor(
            onSuccess = {
                doctors.clear()
                doctors.addAll(it)
            },
            onFailure = {
                Log.e("Firebase", "Error getting documents", it)
            }
        )
    }
}

