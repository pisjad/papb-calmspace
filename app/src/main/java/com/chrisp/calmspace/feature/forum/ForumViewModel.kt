package com.chrisp.calmspace.feature.forum

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.chrisp.calmspace.model.ForumPost

class ForumViewModel(private val repository: PostRepository) : ViewModel() {

    private val _posts = MutableLiveData<List<ForumPost>>()
    val posts: LiveData<List<ForumPost>> get() = _posts

    fun fetchPosts() {
        _posts.value = repository.getPosts()  // Fetch data from repository
    }

    fun addPost(post: ForumPost) {
        repository.addPost(post)
        fetchPosts() // Refresh the list after adding
    }

    fun updatePost(post: ForumPost) {
        repository.updatePost(post)
        fetchPosts() // Refresh the list after updating
    }

    fun deletePost(post: ForumPost) {
        repository.deletePost(post)
        fetchPosts() // Refresh the list after deleting
    }
}

