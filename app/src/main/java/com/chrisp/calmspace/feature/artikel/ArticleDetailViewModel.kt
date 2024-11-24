package com.chrisp.calmspace.feature.artikel

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.chrisp.calmspace.feature.data.Repository
import com.chrisp.calmspace.model.ArticleModel

class ArticleDetailViewModel : ViewModel() {
    val repository = Repository()
    val article = mutableStateOf<ArticleModel?>(null)

    fun getArticleById(article_id: String){
        repository.getArticleById(
            article_id,
            onSuccess = {
                article.value = it
            },
            onFailed = {
                Log.e("Gagal", it.toString())
            }
        )
    }
}