package com.chrisp.calmspace.feature.auth

import com.chrisp.calmspace.R
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.chrisp.calmspace.ui.theme.Purple100
import com.chrisp.calmspace.ui.theme.White100
import com.chrisp.calmspace.ui.theme.ChevronLeft
import com.chrisp.calmspace.ui.theme.White40
import androidx.compose.runtime.*
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.chrisp.calmspace.navigation.Screen
import kotlinx.coroutines.delay

@Composable
fun LoginScreen(
    navController: NavController
) {
    val viewModel: AuthViewModel = viewModel()
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var isLoading by remember { mutableStateOf(false) }

    LaunchedEffect(viewModel.errMsg.value) {
        if (viewModel.errMsg.value.isNotEmpty()) {
            delay(3000)
            viewModel.errMsg.value = ""
        }
    }

    LaunchedEffect(key1 = viewModel.isLogin.value){
        if(viewModel.isLogin.value){
            navController.navigate(Screen.Home.route) {
                popUpTo(Screen.Login.route) {
                    inclusive = true
                }
            }
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
            onClick = {
                navController.navigate(Screen.Onboarding.route) {
                    popUpTo(Screen.Login.route) {
                        inclusive = true
                    }
                }
            },
            modifier = Modifier
                .size(40.dp)
                .align(Alignment.Start)
                .padding(start = 0.dp, bottom = 8.dp)
        ) {
            Icon(
                imageVector = ChevronLeft,
                contentDescription = "Back to Onboarding",
                tint = Purple100
            )
        }

        Text(
            text = "Login",
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp,
            color = Purple100,
            modifier = Modifier
                .padding(start = 10.dp, bottom = 20.dp)
                .align(Alignment.Start)
        )

        Text(
            text = "Hai! Selamat Datang di CalmSpace ✨",
            fontSize = 15.sp,
            color = White100,
            modifier = Modifier
                .padding(start = 10.dp, bottom = 20.dp)
                .align(Alignment.Start)
        )

        // Email Field
        Text(
            text = "Email",
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
            color = Purple100,
            modifier = Modifier
                .padding(start = 10.dp, bottom = 5.dp)
                .align(Alignment.Start)
        )
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            placeholder = { Text("Masukan Email anda") },
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
            modifier = Modifier
                .padding(start = 10.dp, bottom = 5.dp)
                .align(Alignment.Start)
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
        viewModel.errMsg.value.let {
            Text(
                text = it,
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodySmall
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        if (viewModel.isLoading.value){
            Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center){
                CircularProgressIndicator()
            }
        }else {
            Button(
                onClick = {
                    if(email == "" || password == ""){
                        viewModel.errMsg.value = "Harap isi semua kolom"
                    }else{
                        viewModel.signIn(
                            email, password
                        )
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp)
                    .padding(start = 10.dp, end = 10.dp),
                shape = MaterialTheme.shapes.medium, // Rounded corners
                elevation = ButtonDefaults.buttonElevation(defaultElevation = 4.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Purple100)
            ) {
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
            elevation = ButtonDefaults.buttonElevation(defaultElevation = 4.dp),
            colors = ButtonDefaults.buttonColors(containerColor = White40)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = painterResource(id = R.drawable.google),
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
                modifier = Modifier.clickable {
                    navController.navigate(Screen.Register.route) {
                        popUpTo(Screen.Login.route) {
                            inclusive = true
                        }
                    }
                }
            )
        }
    }
}