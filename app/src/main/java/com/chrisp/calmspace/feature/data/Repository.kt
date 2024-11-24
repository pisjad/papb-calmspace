package com.chrisp.calmspace.feature.data

import com.chrisp.calmspace.model.ForumModel
import com.chrisp.calmspace.model.UserModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class Repository{
    private val firestore: FirebaseFirestore = FirebaseFirestore.getInstance()
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()

    fun signUp(
        email : String,
        password : String,
        name: String,
        no_telp: String,
        onSuccess: () -> Unit,
        onFailed: (Exception) -> Unit
    ){
        auth.createUserWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                firestore
                    .collection("user")
                    .document(it.user?.uid ?: "")
                    .set(
                        UserModel(
                            uid = it.user?.uid ?: "",
                            name = name,
                            no_telp = no_telp,
                            email = it.user?.email ?: "",
                        )
                    )
                    .addOnSuccessListener {
                        onSuccess()
                    }
                    .addOnFailureListener {
                        onFailed(it)
                    }
            }
            .addOnFailureListener{
                onFailed(it)
            }
    }

    fun signIn(
        email: String,
        password: String,
        onSuccess: (Boolean) -> Unit,
        onFailed: (Exception) -> Unit
    ){
        auth.signInWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                onSuccess(true)
            }
            .addOnFailureListener {
                onFailed(it)
            }
    }

    fun getUser(
        uid: String,
        onSuccess: (UserModel) -> Unit,
        onFailed: (Exception) -> Unit,
    ){
        firestore
            .collection("user")
            .document(uid)
            .addSnapshotListener { value, error ->
                if (error != null) {
                    onFailed(error)
                }
                value?.let { doc ->
                    onSuccess(
                        UserModel(
                            uid = auth.uid ?: "",
                            name = doc["name"] as String,
                            email = auth.currentUser?.email ?: "",
                            no_telp = doc["no_telp"] as String,
                        )
                    )
                    return@addSnapshotListener
                }
            }
    }

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

    fun logout(onSuccess: () -> Unit, onFailed: (Exception) -> Unit) {
        try {
            auth.signOut()
            onSuccess()
        } catch (e: Exception) {
            onFailed(e)
        }
    }

}