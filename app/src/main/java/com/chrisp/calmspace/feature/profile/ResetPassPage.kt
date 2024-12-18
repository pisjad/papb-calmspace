package com.chrisp.calmspace.feature.profile

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.chrisp.calmspace.navigation.Screen
import com.chrisp.calmspace.ui.theme.ChevronLeft
import com.chrisp.calmspace.ui.theme.Purple100
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.*

@Composable
fun ResetPasswordPage(navController: NavController) {
    val auth = FirebaseAuth.getInstance()
    val currentUser = auth.currentUser

    var newPassword by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    var message by remember { mutableStateOf("") }

    Box(modifier = Modifier.fillMaxSize()) {
        IconButton(
            onClick = {
                navController.navigate(Screen.Profile.route) {
                    popUpTo(Screen.ResetPass.route) { inclusive = true }
                }
            },
            modifier = Modifier
                .size(40.dp)
                .align(Alignment.TopStart)
                .padding(start = 16.dp, top = 24.dp)
        ) {
            Icon(
                imageVector = ChevronLeft,
                contentDescription = "Chevron Left Button",
                tint = Purple100
            )
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Reset Password", style = MaterialTheme.typography.headlineMedium)

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = newPassword,
                onValueChange = { newPassword = it },
                label = { Text("New Password") },
                visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                modifier = Modifier.fillMaxWidth(),
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = confirmPassword,
                onValueChange = { confirmPassword = it },
                label = { Text("Confirm Password") },
                visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    if (newPassword == confirmPassword) {
                        currentUser?.updatePassword(newPassword)?.addOnCompleteListener { task ->
                            message = if (task.isSuccessful) {
                                "Password successfully updated!"
                            } else {
                                task.exception?.localizedMessage ?: "Failed to update password."
                            }
                        }

                    } else {
                        message = "Passwords do not match."
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Reset Password")
            }

            Spacer(modifier = Modifier.height(16.dp))

            if (message.isNotEmpty()) {
                Text(text = message, color = MaterialTheme.colorScheme.error)
            }
        }
    }
}

