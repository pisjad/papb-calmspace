package com.chrisp.calmspace.feature.forum

import androidx.room.*
import com.chrisp.calmspace.model.ForumPost

@Dao
interface PostDao {

    // Menambahkan post baru
    @Insert
    fun insert(post: ForumPost)

    // Mengambil semua post
    @Query("SELECT * FROM posts")
    fun getAllPosts(): List<ForumPost>

    // Memperbarui post
    @Update
    fun updatePost(post: ForumPost)

    // Menghapus post
    @Delete
    fun deletePost(post: ForumPost)
}
