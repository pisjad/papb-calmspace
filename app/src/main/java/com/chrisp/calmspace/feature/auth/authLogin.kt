package com.chrisp.calmspace.feature.auth

import android.R
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.chrisp.calmspace.ui.theme.Purple100
import com.chrisp.calmspace.ui.theme.White100
import androidx.compose.material.ButtonDefaults
import com.chrisp.calmspace.ui.theme.ChevronLeft
import com.chrisp.calmspace.ui.theme.White40
import androidx.compose.runtime.*
import androidx.compose.material3.*
import androidx.compose.foundation.layout.*
import androidx.lifecycle.viewmodel.compose.viewModel


@Composable
fun LoginScreen(onLoginSuccess: (String) -> Unit, viewModel: AuthViewModel = viewModel()) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val currentUser by remember { viewModel.currentUser }
    val errorMessage by remember { viewModel.errorMessage }
    var isLoading by remember { mutableStateOf(false) }

    // Check if the user is logged in (in case of a session restore)
    LaunchedEffect(currentUser) {
        if (currentUser != null) {
            // If logged in, pass the username to the next screen
            onLoginSuccess(currentUser?.email ?: "User")
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        IconButton(
            onClick = { /* Handle click action */ },
            modifier = Modifier
                .size(40.dp)
                .align(Alignment.Start)
                .padding(start = 0.dp, bottom = 8.dp)
        ) {
            Icon(
                imageVector = ChevronLeft,
                contentDescription = "Chevron Left Button",
                tint = Purple100
            )
        }

        Text(
            text = "Login",
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp,
            color = Purple100,
            modifier = Modifier.padding(start = 10.dp, bottom = 20.dp).align(Alignment.Start)
        )

        Text(
            text = "Hai! Selamat Datang di CalmSpace ✨",
            fontSize = 15.sp,
            color = White100,
            modifier = Modifier.padding(start = 10.dp, bottom = 20.dp).align(Alignment.Start)
        )

        // Email Field
        Text(
            text = "Email",
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
            color = Purple100,
            modifier = Modifier.padding(start = 10.dp, bottom = 5.dp).align(Alignment.Start)
        )
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            placeholder = { Text("Minimal 8 karakter") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 10.dp, end = 10.dp),
            visualTransformation = VisualTransformation.None,
            isError = false,
            singleLine = true,
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next),
            keyboardActions = KeyboardActions.Default
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Password Field
        Text(
            text = "Password",
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
            color = Purple100,
            modifier = Modifier.padding(start = 10.dp, bottom = 5.dp).align(Alignment.Start)
        )
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            placeholder = { Text("Masukkan password") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 10.dp, end = 10.dp),
            visualTransformation = PasswordVisualTransformation(),
            isError = false,
            singleLine = true,
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions.Default
        )

        // Show error message if exists
        errorMessage?.let {
            Text(
                text = it,
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodySmall
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Login Button (show loading spinner when clicked)
        Button(
            onClick = {
                viewModel.login(email, password) { success, error ->
                    if (success) {
                        onLoginSuccess(email)  // Navigate to the next screen on successful login
                    } else {
                        viewModel.errorMessage.value = error
                    }
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
                .padding(start = 10.dp, end = 10.dp),
            shape = MaterialTheme.shapes.medium, // Rounded corners
            elevation = ButtonDefaults.elevation(defaultElevation = 4.dp),
            colors = ButtonDefaults.buttonColors(backgroundColor = Purple100)
        ) {
            if (isLoading) {
                // Resolving ambiguity by providing only the necessary parameters
//                CircularProgressIndicator(
//                    modifier = Modifier.size(24.dp).align(Alignment.CenterVertically),
//                    color = Color.White // Only specify color
//                )
            } else {
                Text(text = "Login", color = Color.White) // Set text color to white for contrast
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Or Section
        Text(text = "Atau", style = MaterialTheme.typography.bodySmall)

        Spacer(modifier = Modifier.height(16.dp))

        // Google Login Button (Handle Google Login)
        Button(
            onClick = { /* Handle Google Login */ },
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
                .padding(start = 10.dp, end = 10.dp),
            shape = MaterialTheme.shapes.medium,
            elevation = ButtonDefaults.elevation(defaultElevation = 4.dp),
            colors = ButtonDefaults.buttonColors(backgroundColor = White40)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = painterResource(id = R.drawable.stat_sys_vp_phone_call_on_hold),
                    contentDescription = "Google Icon",
                    modifier = Modifier.size(24.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = "Login dengan Google", color = White100)
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Register Link
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
            Text(text = "Belum memiliki akun? ")
            Text(
                text = "Register",
                modifier = Modifier.clickable { /* Handle Register Navigation */ }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewLoginScreen() {
    LoginScreen(onLoginSuccess = {})
}