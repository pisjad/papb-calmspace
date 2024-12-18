package com.chrisp.calmspace.feature.forum

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.chrisp.calmspace.feature.data.Repository
import com.chrisp.calmspace.model.ForumModel
import com.google.firebase.firestore.FirebaseFirestore

class ForumDetailViewModel : ViewModel() {
    val repository = Repository()
    val forum = mutableStateOf<ForumModel?>(null)

    fun getForumById(forum_id: String){
        repository.getForumById(
            forum_id,
            onSuccess = {
                forum.value = it
            },
            onFailed = {
                Log.e("Gagal", it.toString())
            }
        )
    }
    fun deleteForum(forumId: String, onSuccess: () -> Unit, onFailure: (String) -> Unit) {
        val firestore = FirebaseFirestore.getInstance()
        firestore.collection("forum").document(forumId)
            .delete()
            .addOnSuccessListener {
                onSuccess()
            }
            .addOnFailureListener { exception ->
                onFailure(exception.message ?: "Failed to delete forum")
            }
    }

}