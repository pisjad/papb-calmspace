package com.chrisp.calmspace.feature.forum

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.size.Size
import com.chrisp.calmspace.model.ForumModel
import com.chrisp.calmspace.navigation.BottomNavigationBar
import com.chrisp.calmspace.navigation.Screen
import com.chrisp.calmspace.ui.theme.Purple80

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ForumScreen(navController: NavController) {
    val forumViewModel: ForumViewModel = viewModel()


    // UI Content
    Scaffold(
        topBar = {
            androidx.compose.material.TopAppBar(
                title = {
                    Row(
                        modifier = Modifier
                            .padding(top = 30.dp)

                    ) {
                        Text("Sharing Session", style = androidx.compose.material.MaterialTheme.typography.h6.copy(color = Color.White))
                    }
                },
                backgroundColor = Purple80,
                modifier = Modifier.height(130.dp),
            )
        },

        bottomBar = { BottomNavigationBar(navController) },


        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    navController.navigate(Screen.ForumAdd.route) {
                        popUpTo(Screen.Forum.route) {
                            inclusive = true
                        }
                    }
                },
                modifier = Modifier.padding(16.dp)
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add Post")
            }
        }
    ){paddingValues ->
        if (forumViewModel.posts.isEmpty()) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text("No posts available.")
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .padding(paddingValues)
                    .padding(16.dp)
            ) {
                items(forumViewModel.posts) { post ->
                    PostCard(
                        post = post,
                        onPostClick = {
                            navController.navigate("${Screen.ForumDetail.route}/${post?.id ?: ""}") {
                                popUpTo(Screen.Forum.route) {
                                    inclusive = true
                                }
                            }
                        }
                    )
                    Spacer(modifier = Modifier.height(10.dp)) // Jarak antar card
                }
            }

        }
    }
}


@Composable
fun PostCard(post: ForumModel?, onPostClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 8.dp)
            .clickable { onPostClick() },
        elevation = CardDefaults.cardElevation(8.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White) // Menentukan warna putih untuk Card
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "Anonymous",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp
            )
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = post?.isi ?: "",
                style = MaterialTheme.typography.bodyMedium,
                fontSize = 18.sp
            )
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "78 Likes")
                Text(text = "2 Comments")
            }
        }
    }
}

