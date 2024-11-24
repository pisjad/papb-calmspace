// File: app/src/main/java/com/chrisp/calmspace/model/ArticleModel.kt

package com.chrisp.calmspace.model

data class ArticleModel(
    val id: String,
    val title: String,      // Judul artikel
    val content: String,    // Konten artikel
    val category: String,   // Kategori (misal: "Artikel" atau "Video")
    val date: String,       // Tanggal publikasi
    val author: String      // Penulis artikel
)