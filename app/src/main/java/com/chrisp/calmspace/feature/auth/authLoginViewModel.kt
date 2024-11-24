package com.chrisp.calmspace.feature.auth

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.chrisp.calmspace.feature.data.Repository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser


class AuthViewModel : ViewModel() {
    val authRepository = Repository()

    val isLogin = mutableStateOf(false)
    val isLoading = mutableStateOf(false)

    val errMsg = mutableStateOf("")

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