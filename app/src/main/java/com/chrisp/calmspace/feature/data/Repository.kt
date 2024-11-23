package com.chrisp.calmspace.feature.data

import com.chrisp.calmspace.model.ForumModel
import com.chrisp.calmspace.navigation.Screen
import com.google.firebase.firestore.FirebaseFirestore

class Repository constructor(
    private val firestore: FirebaseFirestore

){
    fun getAllForum(
        onSuccess: (List<ForumModel>) -> Unit,
        onFailure: (Exception) -> Unit
    ){
        firestore.collection("forum")
            .addSnapshotListener { snapshot, exception ->
                if (exception != null) {
                    onFailure(exception)
                    return@addSnapshotListener
                }
                snapshot?.let {
                    onSuccess(it.documents.mapNotNull { document ->
                        ForumModel(
                            id = document?.getString("id") ?: "",
                            isi = document?.getString("isi") ?: ""
                        )
                    })
                    return@addSnapshotListener
                }
            }
    }
}