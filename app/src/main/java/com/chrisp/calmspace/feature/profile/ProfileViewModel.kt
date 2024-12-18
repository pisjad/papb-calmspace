package com.chrisp.calmspace.feature.profile

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chrisp.calmspace.feature.data.Repository
import com.chrisp.calmspace.model.ProfileModel
import com.chrisp.calmspace.model.UserModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ProfileViewModel : ViewModel() {
    val auth: FirebaseAuth = FirebaseAuth.getInstance()
    val repository = Repository()
    val user = mutableStateOf<UserModel?>(null)

    private val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()

    fun deleteAccount(onSuccess: () -> Unit, onFailure: (String) -> Unit) {
        val user = FirebaseAuth.getInstance().currentUser
        val uid = user?.uid ?: return onFailure("User not logged in")

        val firestore = FirebaseFirestore.getInstance()

        // Hapus data dari Firestore BAHAYA GAIS MAKANYA AKU GANTI COLE TION
        firestore.collection("users123113").document(uid)
            .delete()
            .addOnSuccessListener {
                // Hapus pengguna dari Firebase Authentication
                user.delete()
                    .addOnSuccessListener {
                        onSuccess()
                    }
                    .addOnFailureListener { exception ->
                        onFailure("Failed to delete account: ${exception.message}")
                    }
            }
            .addOnFailureListener { exception ->
                onFailure("Failed to delete user data: ${exception.message}")
            }
    }


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
