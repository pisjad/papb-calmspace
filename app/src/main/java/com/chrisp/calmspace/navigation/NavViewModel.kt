package com.chrisp.calmspace.navigation

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth

class NavViewModel : ViewModel() {
    val auth: FirebaseAuth = FirebaseAuth.getInstance()
}