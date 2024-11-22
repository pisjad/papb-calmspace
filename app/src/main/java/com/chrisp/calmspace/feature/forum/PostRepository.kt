package com.chrisp.calmspace.feature.forum

import android.content.Context
import androidx.room.Room
import com.chrisp.calmspace.model.ForumPost

class PostRepository(context: Context) {

    private val db = Room.databaseBuilder(context, AppDatabase::class.java, "forum_db").build()
    private val postDao = db.postDao()

    // Menyimpan post baru
    fun addPost(post: ForumPost) {
        postDao.insert(post)
    }

    // Mengambil semua post
    fun getPosts(): List<ForumPost> {
        return postDao.getAllPosts()
    }

    // Memperbarui post
    fun updatePost(post: ForumPost) {
        postDao.updatePost(post)
    }

    // Menghapus post
    fun deletePost(post: ForumPost) {
        postDao.deletePost(post)
    }
}
