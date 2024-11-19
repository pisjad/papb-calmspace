package com.chrisp.calmspace.feature.forum

import androidx.room.Database
import androidx.room.RoomDatabase
import com.chrisp.calmspace.model.ForumPost

@Database(entities = [ForumPost::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun postDao(): PostDao  // Mendeklarasikan DAO yang akan digunakan
}
