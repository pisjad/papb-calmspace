package com.chrisp.calmspace.feature.forum

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.chrisp.calmspace.R
import com.chrisp.calmspace.feature.artikel.ArticleDetailViewModel
import com.chrisp.calmspace.navigation.BottomNavigationBar
import com.chrisp.calmspace.navigation.Screen
import com.chrisp.calmspace.ui.theme.Purple80

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ForumDetailScreen(
    navController: NavController,
    forum_id: String,
    modifier: Modifier = Modifier
) {
    val viewModel: ForumDetailViewModel = viewModel()

    LaunchedEffect(key1 = true) {
        viewModel.getForumById(forum_id)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Sharing Corner") },
                navigationIcon = {
                    IconButton(onClick = {
                        navController.navigate(Screen.Forum.route) {
                            popUpTo(Screen.ForumDetail.route) {
                                inclusive = true
                            }
                        }
                    }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                },
                actions = {
                    IconButton(onClick = {
                        // Panggil fungsi untuk menghapus forum
                        viewModel.deleteForum(
                            forumId = forum_id,
                            onSuccess = {
                                navController.navigate(Screen.Forum.route) {
                                    popUpTo(Screen.ForumDetail.route) {
                                        inclusive = true
                                    }
                                }
                            },
                            onFailure = { message ->
                                // Tampilkan pesan error (opsional)
                            }
                        )
                    }) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_delete),
                            contentDescription = "Delete",
                            tint = Color.White
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Purple80,
                )
            )
        },
        bottomBar = {
            BottomNavigationBar(navController)
        }
    ) { paddingValues ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Text(
                    text = "Anonim",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(8.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = viewModel.forum.value?.isi ?: "",
                        fontSize = 14.sp,
                        color = Color.Gray
                    )
                }
            }
        }
    }
}

