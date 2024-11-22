package com.chrisp.calmspace.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "posts")
data class ForumPost(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,  // id otomatis di-generate
    val userName: String,
    val content: String,
    val likes: Int,
    val comments: Int
)