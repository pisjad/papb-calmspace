package com.chrisp.calmspace.feature.profile

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chrisp.calmspace.feature.data.Repository
import com.chrisp.calmspace.model.ProfileModel
import com.chrisp.calmspace.model.UserModel
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ProfileViewModel : ViewModel() {
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

    fun logout(onSuccess: () -> Unit, onFailed: (Exception) -> Unit) {
        repository.logout(onSuccess, onFailed)
    }
}
