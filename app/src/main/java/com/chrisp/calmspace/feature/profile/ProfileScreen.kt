package com.chrisp.calmspace.feature.profile

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.chrisp.calmspace.R
import com.chrisp.calmspace.navigation.BottomNavigationBar
import com.chrisp.calmspace.navigation.Screen
import com.chrisp.calmspace.ui.theme.Danger
import com.chrisp.calmspace.ui.theme.Purple100
import com.chrisp.calmspace.ui.theme.Purple60
import com.chrisp.calmspace.ui.theme.Purple80
import com.chrisp.calmspace.ui.theme.White20
import com.chrisp.calmspace.ui.theme.White60
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ProfileScreen(navController: NavController) {
    val viewModel: ProfileViewModel = viewModel()
    val systemUiController = rememberSystemUiController()

    // Set the status bar color
    SideEffect {
        systemUiController.setStatusBarColor(color = Purple80)
    }

    Scaffold(
        bottomBar = {
            BottomNavigationBar(navController)
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Profil",
                        style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 20.sp),
                        color = MaterialTheme.colorScheme.onPrimary,
                    )
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Purple80,
                    titleContentColor = Purple80
                )
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Profile Picture Section
            Box(contentAlignment = Alignment.BottomEnd) {
                // Placeholder for profile picture, replace with actual image loading
                Icon(
                    painter = painterResource(id = R.drawable.ic_profil), // Replace with your icon resource
                    contentDescription = "Edit Profile Picture",
                    tint = White60,
                    modifier = Modifier
                        .size(80.dp)
                        .background(Color.White, CircleShape)
                        .clickable { /* Handle picture update */ }
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Name and Email Section
            Text(
                text = viewModel.user.value?.name ?: "",
                style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 20.sp, color = Purple100)
            )
            Text(
                text = viewModel.user.value?.email ?: "",
                style = TextStyle(fontSize = 16.sp, color = Purple100)
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Options Section
            OptionItem(icon = R.drawable.ic_riwayat_konsultasi, text = "Riwayat Konsultasi") {
                /* Navigate to consultation history */
            }
            OptionItem(icon = R.drawable.ic_reset_password, text = "Reset Password") {
                /* Navigate to reset password */
            }

            Spacer(modifier = Modifier.weight(1f))

            // Logout Button with Material3 Button
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
                colors = ButtonDefaults.buttonColors(containerColor = Danger),
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
                    .height(48.dp)
            ) {
                Text(text = "Keluar", color = Color.White, fontSize = 16.sp)
            }

            Spacer(modifier = Modifier.height(50.dp))
        }
    }
}

@Composable
fun OptionItem(icon: Int, text: String, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .clickable { onClick() },
        colors = CardDefaults.cardColors(containerColor = Color.White),
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = icon),
                contentDescription = null,
                tint = White20,
                modifier = Modifier
                    .size(33.dp)
                    .background(Purple60, RoundedCornerShape(4.dp))
                    .padding(4.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = text,
                fontSize = 16.sp,
                color = Purple100,
                modifier = Modifier.weight(1f)
            )
            Icon(
                painter = painterResource(id = R.drawable.ic_arrow_right), // Replace with your right arrow icon
                contentDescription = null,
                tint = Color.Gray,
                modifier = Modifier.size(24.dp)
            )
        }
    }
}