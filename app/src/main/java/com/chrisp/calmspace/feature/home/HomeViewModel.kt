package com.chrisp.calmspace.feature.auth

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.chrisp.calmspace.feature.data.Repository
import com.chrisp.calmspace.model.UserModel
import com.google.firebase.auth.FirebaseAuth

class HomeViewModel : ViewModel() {
    val auth: FirebaseAuth = FirebaseAuth.getInstance()
    val repository = Repository()
    val user = mutableStateOf<UserModel?>(null)

    init {
        val userId = auth.uid
        if (!userId.isNullOrEmpty()) {
            repository.getUser(
                userId,
                onSuccess = {
                    user.value = it
                },
                onFailed = {
                    Log.e("Gagal", it.toString())
                }
            )
        } else {
            Log.e("Error", "User ID is null or empty. Please log in.")
        }
    }
}
