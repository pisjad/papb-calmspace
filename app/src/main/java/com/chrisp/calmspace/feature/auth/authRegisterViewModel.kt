package com.chrisp.calmspace.feature.auth

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.chrisp.calmspace.feature.data.Repository
import com.chrisp.calmspace.model.UserModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.UserProfileChangeRequest

class RegisterViewModel : ViewModel() {
    val authRepository = Repository()

    val isLoading = mutableStateOf(false)
    val isSuccess = mutableStateOf(false)

    val errMsg = mutableStateOf("")

    fun signUp(
        email : String,
        password : String,
        name : String,
        no_telp : String,
    ){
        isLoading.value = true
        isSuccess.value = false
        authRepository.signUp(
            email,
            password,
            name,
            no_telp,
            onSuccess = {
                isSuccess.value = true
                isLoading.value = false
                Log.e("Berhasil", "Sign Up Berhasil")
            },
            onFailed = {
                isLoading.value = false
                isSuccess.value = false
                errMsg.value = it.toString()
            }
        )
    }
}
