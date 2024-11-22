package com.chrisp.calmspace.feature.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import coil.compose.rememberImagePainter
import com.chrisp.calmspace.R

@Composable
fun ProfileScreen(viewModel: ProfileViewModel = viewModel()) {
    val profile by viewModel.profileState.collectAsState()

    Column(
        modifier = Modifier
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
            AsyncImage(
                model = profile.profilePictureUrl,
                contentDescription = "Profile Picture",
                modifier = Modifier
                    .size(100.dp)
                    .clip(CircleShape)
            )
            Icon(
                painter = painterResource(id = R.drawable.ic_camera), // Replace with your icon resource
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
            text = profile.name,
            style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 20.sp)
        )
        Text(
            text = profile.email,
            style = TextStyle(fontSize = 16.sp, color = Color.Gray)
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Options
        OptionItem(icon = R.drawable.ic_calendar, text = "Riwayat Konsultasi") {
            /* Navigate to consultation history */
        }
        OptionItem(icon = R.drawable.ic_lock, text = "Reset Password") {
            /* Navigate to reset password */
        }

        Spacer(modifier = Modifier.weight(1f))

        // Logout Button
        Button(
            onClick = { /* Handle logout */ },
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

@Preview
@Composable
private fun profileprev() {
    ProfileScreen()
}