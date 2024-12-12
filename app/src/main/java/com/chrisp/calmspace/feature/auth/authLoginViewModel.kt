package com.chrisp.calmspace.feature.auth

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chrisp.calmspace.feature.data.Repository
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


class AuthViewModel() : ViewModel() {
    val authRepository = Repository()
    val isLogin = mutableStateOf(false)
    val isLoading = mutableStateOf(false)
    val errMsg = mutableStateOf("")

    private val _user = MutableStateFlow<FirebaseUser?>(null)
    val user: StateFlow<FirebaseUser?> = _user

    init {
        _user.value = authRepository.getCurrentUser()
    }

    fun signInWithGoogle(idToken: String, onSuccess: () -> Unit, onError: (Exception) -> Unit) {
        viewModelScope.launch {
            try {
                val result = authRepository.signInWithGoogle(idToken)
                _user.value = result.user
                onSuccess()
            } catch (e: Exception) {
                onError(e)
            }
        }
    }

    fun signOut() {
        authRepository.signOut()
        _user.value = null
    }

    fun signIn(
        email : String,
        password : String,
    ){
        isLoading.value = true
        authRepository.signIn(
            email,
            password,
            onSuccess = {
                isLoading.value = false
                isLogin.value = it
            },
            onFailed = {
                isLoading.value = false
                errMsg.value = it.toString()
            }
        )
    }
}