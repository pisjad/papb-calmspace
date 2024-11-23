package com.chrisp.calmspace.feature.forum

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.chrisp.calmspace.model.ForumModel
import com.chrisp.calmspace.navigation.BottomNavigationBar

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ForumScreen(navController: NavController) {
    val forumViewModel: ForumViewModel = viewModel()


    // UI Content
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Sharing Session") },
                actions = {
                    IconButton(onClick = { /* Handle actions */ }) {
                        Icon(Icons.Default.Add, contentDescription = "Add Post")
                    }
                }
            )
        },

        bottomBar = { BottomNavigationBar(navController) },


        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    // Handle the add post action, like opening a dialog or navigating to a new screen
//                    val newPost = ForumPost(userName = "User1", content = "This is a new post", likes = 0, comments = 0)
//                    forumViewModel.addPost(newPost)
                },
                modifier = Modifier.padding(16.dp)  // Optional: adjust the padding
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
            LazyColumn(modifier = Modifier.padding(paddingValues).padding(16.dp)) {
                items(forumViewModel.posts) { post ->
                    PostCard(post = post, onPostClick = {
                        // Handle post click, maybe open a detailed view or edit
                    })
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
        elevation = CardDefaults.cardElevation(8.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = "Anonymous", style = MaterialTheme.typography.titleMedium)
            Text(text = post?.isi ?: "", style = MaterialTheme.typography.bodyMedium)
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
