package com.chrisp.calmspace.feature.auth

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser


class AuthViewModel : ViewModel() {
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()

    // State variables to hold user info and error messages
    var currentUser = mutableStateOf<FirebaseUser?>(null)
    var errorMessage = mutableStateOf<String?>(null)
    var isLoading = mutableStateOf(false)  // To handle loading state

    init {
        // Check if the user is already logged in when the view model is created
        checkUserLoggedIn()
    }

    // Handle login process
    fun login(email: String, password: String, onLoginResult: (Boolean, String?) -> Unit) {
        isLoading.value = true  // Start loading

        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                isLoading.value = false  // Stop loading

                if (task.isSuccessful) {
                    // On success, set the current user and notify UI
                    currentUser.value = auth.currentUser
                    onLoginResult(true, null)  // Login success
                } else {
                    // On failure, set error message and notify UI
                    errorMessage.value = task.exception?.message
                    onLoginResult(false, errorMessage.value)  // Login failure
                }
            }
    }

    // Check if the user is already logged in
    fun checkUserLoggedIn() {
        currentUser.value = auth.currentUser
    }

    // Handle logout process
    fun logout() {
        auth.signOut()
        currentUser.value = null
    }
}