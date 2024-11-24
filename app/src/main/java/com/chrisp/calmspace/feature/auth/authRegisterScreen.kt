
package com.chrisp.calmspace.feature.auth

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.chrisp.calmspace.navigation.Screen
import com.chrisp.calmspace.ui.theme.ChevronLeft
import com.chrisp.calmspace.ui.theme.Purple100
import kotlinx.coroutines.delay

@Composable
fun RegistrationScreen(
    navController: NavController
) {
    val viewModel: RegisterViewModel = viewModel()

    var fullName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var phoneNumber by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    LaunchedEffect(viewModel.errMsg.value) {
        if (viewModel.errMsg.value.isNotEmpty()) {
            delay(3000)
            viewModel.errMsg.value = ""
        }
    }

    LaunchedEffect(key1 = viewModel.isSuccess.value){
        if (viewModel.isSuccess.value) {
            navController.navigate(Screen.Login.route) {
                popUpTo(Screen.Register.route) {
                    inclusive = true
                }
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp, vertical = 80.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        IconButton(onClick = {
            navController.navigate(Screen.Onboarding.route) {
                popUpTo(Screen.Register.route) {
                    inclusive = true
                }
            }
        }, // Navigate back to onboarding
            modifier = Modifier
                .size(40.dp)
                .align(Alignment.Start)
                .padding(start = 0.dp, bottom = 8.dp)) {
            Icon(
                imageVector = ChevronLeft,
                contentDescription = "Chevron Left Button",
                tint = Purple100,
            )
        }
        Text(
            text = "Registrasi",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF3A285F),
            modifier = Modifier
                .align(Alignment.Start)
                .padding(start = 10.dp, bottom = 8.dp)
        )
        Text(
            text = "Silahkan isi data diri anda",
            fontSize = 14.sp,
            color = Color.Gray,
            modifier = Modifier
                .align(Alignment.Start)
                .padding(start = 10.dp, bottom = 24.dp)
        )
        Text(
            text = "Nama Lengkap",
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
            color = Purple100,
            modifier = Modifier.padding(start = 10.dp,bottom = 5.dp).align(Alignment.Start)
        )

        // Full Name Field
        OutlinedTextField(
            value = fullName,
            onValueChange = { fullName = it },
            label = { Text("Nama Lengkap") },
            placeholder = { Text("Masukkan nama lengkap") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 10.dp, end = 10.dp)
                .background(Color(0xFFF5F5F5))
        )
        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Email",
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
            color = Purple100,
            modifier = Modifier.padding(start = 10.dp,bottom = 5.dp).align(Alignment.Start)
        )
        // Email Field
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            placeholder = { Text("Masukkan email") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 10.dp, end = 10.dp)
                .background(Color(0xFFF5F5F5)),
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Email
            )
        )
        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Nomor Telfon",
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
            color = Purple100,
            modifier = Modifier.padding(start = 10.dp,bottom = 5.dp).align(Alignment.Start)
        )
        // Phone Number Field
        OutlinedTextField(
            value = phoneNumber,
            onValueChange = { phoneNumber = it },
            label = { Text("No. Handphone") },
            placeholder = { Text("Masukkan nomor handphone") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 10.dp, end = 10.dp)
                .background(Color(0xFFF5F5F5)),
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Phone
            )
        )
        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Password",
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
            color = Purple100,
            modifier = Modifier.padding(start = 10.dp,bottom = 5.dp).align(Alignment.Start)
        )
        // Password Field
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            placeholder = { Text("Masukkan password") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 10.dp, end = 10.dp)
                .background(Color(0xFFF5F5F5)),
        )
        Spacer(modifier = Modifier.height(24.dp))
        if (viewModel.isLoading.value){
            Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center){
                CircularProgressIndicator()
            }
        }else {
            Button(
                onClick = {
                    if(email == "" || password == "" || fullName == "" || phoneNumber == ""){
                        viewModel.errMsg.value = "Harap isi semua kolom"
                    }else{
                        viewModel.signUp(email, password, fullName, phoneNumber)
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp)
                    .padding(start = 10.dp, end = 10.dp),
                colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF3A285F))
            ) {
                Text(text = "Daftar", color = Color.White)
            }
        }

        Text(
            text = viewModel.errMsg.value,
            color = Color.Red,
            modifier = Modifier.padding(top = 8.dp)
        )
    }
//    Spacer(modifier = Modifier.height(16.dp))
//
//    // Or Divider
//    Row(
//        verticalAlignment = Alignment.CenterVertically,
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(horizontal = 10.dp)
//    ) {
//        Divider(modifier = Modifier.weight(1f))
//        Text(
//            text = "Atau",
//            modifier = Modifier.padding(horizontal = 8.dp),
//            color = Color.Gray
//        )
//        Divider(modifier = Modifier.weight(1f))
//    }
//    Spacer(modifier = Modifier.height(16.dp))

    // Google Sign-Up Button
//    OutlinedButton(
//        onClick = { /* Handle Google Sign-Up */ },
//        modifier = Modifier
//            .fillMaxWidth()
//            .height(48.dp)
//            .padding(start = 10.dp, end = 10.dp),
//        colors = ButtonDefaults.outlinedButtonColors(backgroundColor = Color(0xFFF5F5F5)) ,
//        elevation = ButtonDefaults.elevation(defaultElevation = 4.dp)
//    ) {
//        Row(verticalAlignment = Alignment.CenterVertically) {
//            // Uncomment and set the Google icon if available
////            Icon(
////                painter = painterResource(id = R.drawable.google),
////                contentDescription = "Google Icon",
////                modifier = Modifier.size(24.dp)
////            )
//            Spacer(modifier = Modifier.width(8.dp))
//            Text(text = "Daftar dengan Google", color = Color.Black)
//        }
//    }
}

