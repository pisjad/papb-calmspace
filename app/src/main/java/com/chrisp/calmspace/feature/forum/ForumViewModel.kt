package com.chrisp.calmspace.feature.forum

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.chrisp.calmspace.feature.data.Repository
import com.chrisp.calmspace.model.ForumModel
import com.google.firebase.firestore.FirebaseFirestore

class ForumViewModel: ViewModel() {
    val repository = Repository()
    val posts = mutableStateListOf<ForumModel?>(null)

  init{
       repository.getAllForum(
           onSuccess = {
               posts.clear()
               posts.addAll(it)
           },
           onFailure = {
                Log.e("Firebase", "Error getting documents", it)
           }
       )
   }

}

