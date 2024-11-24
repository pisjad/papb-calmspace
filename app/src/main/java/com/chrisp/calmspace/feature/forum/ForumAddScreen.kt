package com.chrisp.calmspace.feature.forum

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.chrisp.calmspace.feature.auth.AuthViewModel
import com.chrisp.calmspace.navigation.Screen
import com.chrisp.calmspace.ui.theme.ChevronLeft
import com.chrisp.calmspace.ui.theme.Purple100
import com.chrisp.calmspace.ui.theme.Purple80

@Composable
fun ForumAddScreen(navController: NavController) {
    val viewModel: ForumAddViewModel = viewModel()

    val context = LocalContext.current

    LaunchedEffect(key1 = viewModel.isSuccess.value){
        if(viewModel.isSuccess.value){

            navController.navigate(Screen.Forum.route) {
                popUpTo(Screen.ForumAdd.route) {
                    inclusive = true
                }
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Purple80) // Menggunakan Purple100
            .padding(16.dp, top = 40.dp, bottom = 16.dp, end = 10.dp),
    ) {
        // Baris untuk ikon dan teks nama
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(bottom = 16.dp),
        ) {
            IconButton(
                onClick = {
                    navController.navigate(Screen.Forum.route) {
                        popUpTo(Screen.ForumAdd.route) {
                            inclusive = true
                        } }
                },
                modifier = Modifier.size(30.dp)
            ) {
                Icon(
                    imageVector = ChevronLeft,
                    contentDescription = "Chevron Left Button",
                    tint = Color.White
                )
            }

        }

        // TextField untuk berbagi cerita
        val textState = remember { androidx.compose.runtime.mutableStateOf("") }
        TextField(
            value = textState.value,
            onValueChange = { textState.value = it },
            placeholder = { Text("Bagikan cerita kamu!",  color= Color.White) },
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        )

        // Tombol Kirim
        Button(
            onClick = {
                if (textState.value.isNotBlank()) {
                    viewModel.addForumPost(textState.value)// Memanggil fungsi ViewModel untuk mengirim data
                } else {
                    Toast.makeText(context, "Cerita tidak boleh kosong!", Toast.LENGTH_SHORT).show()
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
                .size(50.dp),
            colors = ButtonDefaults.buttonColors(backgroundColor = Color.White),
        ) {
            if (viewModel.isLoading.value) {
                CircularProgressIndicator(
                    color = MaterialTheme.colors.primary,
                    modifier = Modifier.size(24.dp)
                )
            } else {
                Text("Kirim Cerita", fontSize = 20.sp)
            }
        }

    }
}