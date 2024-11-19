package com.chrisp.calmspace.feature.auth

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.UserProfileChangeRequest

class RegisterViewModel : ViewModel() {
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()

    // State variables for registration
    var currentUser = mutableStateOf<FirebaseUser?>(null)
    var errorMessage = mutableStateOf<String?>(null)
    var isLoading = mutableStateOf(false)

    // Handle registration process
    fun register(
        fullName: String,
        email: String,
        password: String,
        onRegisterResult: (Boolean, String?) -> Unit
    ) {
        isLoading.value = true

        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                isLoading.value = false

                if (task.isSuccessful) {
                    val user = auth.currentUser

                    // Update user profile with full name
                    val profileUpdates = UserProfileChangeRequest.Builder()
                        .setDisplayName(fullName)
                        .build()

                    user?.updateProfile(profileUpdates)
                        ?.addOnCompleteListener { profileTask ->
                            if (profileTask.isSuccessful) {
                                currentUser.value = user
                                onRegisterResult(true, null) // Registration successful
                            } else {
                                errorMessage.value = profileTask.exception?.message
                                onRegisterResult(false, errorMessage.value) // Profile update failed
                            }
                        }
                } else {
                    errorMessage.value = task.exception?.message
                    onRegisterResult(false, errorMessage.value) // Registration failed
                }
            }
    }
}
