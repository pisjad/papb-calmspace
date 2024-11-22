package com.chrisp.calmspace.feature.forum

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import com.chrisp.calmspace.model.ForumPost

class ForumActivity : ComponentActivity() {

    private val forumViewModel: ForumViewModel by viewModels { ForumViewModelFactory() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ForumScreen(forumViewModel)
        }
    }
}

class ForumViewModelFactory :
    ViewModelProvider.Factory

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ForumScreen(forumViewModel: ForumViewModel) {
    // Fetching posts from ViewModel
    forumViewModel.fetchPosts()

    // Observe posts LiveData
    val posts = forumViewModel.posts.value

    // UI Content
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Forum") },
                actions = {
                    IconButton(onClick = { /* Handle actions */ }) {
                        Icon(Icons.Default.Add, contentDescription = "Add Post")
                    }
                }
            )
        },
        content = {
            if (posts.isNullOrEmpty()) {
                // Show a placeholder when no posts are available
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text("No posts available.")
                }
            } else {
                LazyColumn(modifier = Modifier.padding(16.dp)) {
                    items(posts) { post ->
                        PostCard(post = post, onPostClick = {
                            // Handle post click, maybe open a detailed view or edit
                        })
                    }
                }
            }
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    // Handle the add post action, like opening a dialog or navigating to a new screen
                    val newPost = ForumPost(userName = "User1", content = "This is a new post", likes = 0, comments = 0)
                    forumViewModel.addPost(newPost)
                },
                modifier = Modifier.padding(16.dp)  // Optional: adjust the padding
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add Post")
            }
        }
    )
}


@Composable
fun PostCard(post: ForumPost, onPostClick: (ForumPost) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 8.dp)
            .clickable { onPostClick(post) },
        elevation = CardDefaults.cardElevation(8.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = post.userName, style = MaterialTheme.typography.titleMedium)
            Text(text = post.content, style = MaterialTheme.typography.bodyMedium)
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "${post.likes} Likes")
                Text(text = "${post.comments} Comments")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewForumScreen() {
    ForumScreen(forumViewModel = viewModel())
}
