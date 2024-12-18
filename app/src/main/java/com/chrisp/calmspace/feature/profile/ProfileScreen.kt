package com.chrisp.calmspace.feature.profile

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.compose.rememberImagePainter
import com.chrisp.calmspace.R
import com.chrisp.calmspace.navigation.BottomNavigationBar
import com.chrisp.calmspace.navigation.NavViewModel
import com.chrisp.calmspace.navigation.Screen

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ProfileScreen(navController: NavController) {
    val viewModel: ProfileViewModel = viewModel()
    val context = LocalContext.current

    Scaffold(
        bottomBar = {
            BottomNavigationBar(navController)
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .background(MaterialTheme.colors.background),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TopAppBar(
                backgroundColor = Color(0xFF7E57C2),
                contentColor = Color.White,
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Profil",
                        style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 20.sp),
                        color = Color.White
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Profile Picture
            Box(contentAlignment = Alignment.BottomEnd) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_camera),
                    contentDescription = "Edit Profile Picture",
                    tint = Color.White,
                    modifier = Modifier
                        .size(24.dp)
                        .background(Color(0xFF7E57C2), CircleShape)
                        .clickable { /* Handle picture update */ }
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Name and Email
            Text(
                text = viewModel.user.value?.name ?: "",
                style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 20.sp)
            )
            Text(
                text = viewModel.user.value?.email ?: "",
                style = TextStyle(fontSize = 16.sp, color = Color.Gray)
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Options
            OptionItem(icon = R.drawable.ic_calendar, text = "Riwayat Konsultasi") {
                /* Navigate to consultation history */
            }
            OptionItem(icon = R.drawable.ic_lock, text = "Reset Password") {
                navController.navigate(Screen.ResetPass.route) {
                    popUpTo(Screen.Profile.route) { inclusive = true }
                }
            }
            OptionItem(icon = R.drawable.ic_delete, text = "Delete Account") {
                viewModel.deleteAccount(
                    onSuccess = {
                        navController.navigate(Screen.Login.route) {
                            popUpTo(Screen.Home.route) { inclusive = true }
                        }
                    },
                    onFailure = { message ->
                        // Tampilkan pesan error jika gagal
                        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
                    }
                )
            }

            Spacer(modifier = Modifier.weight(1f))

            // Logout Button
            Button(
                onClick = {
                    viewModel.logout(onSuccess = {
                        navController.navigate(Screen.Login.route) {
                            popUpTo(Screen.Home.route) { inclusive = true }
                        }
                    }, onFailed = {
                        // Handle logout failure
                    })
                },
                colors = ButtonDefaults.buttonColors(backgroundColor = Color.Red),
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
                    .height(48.dp)
            ) {
                Text(text = "Keluar", color = Color.White)
            }
        }
    }
}

@Composable
fun OptionItem(icon: Int, text: String, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(horizontal = 16.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(id = icon),
            contentDescription = null,
            tint = Color(0xFF7E57C2),
            modifier = Modifier.size(24.dp)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(text = text, fontSize = 16.sp, color = Color.Black)
    }
}
