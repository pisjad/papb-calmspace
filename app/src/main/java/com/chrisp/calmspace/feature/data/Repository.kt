package com.chrisp.calmspace.feature.data

import com.chrisp.calmspace.model.ArticleModel
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

    fun getAllArticle(
        onSuccess: (List<ArticleModel>) -> Unit,
        onFailure: (Exception) -> Unit
    ){
        firestore.collection("article")
            .addSnapshotListener { snapshot, exception ->
                if (exception != null) {
                    onFailure(exception)
                    return@addSnapshotListener
                }
                snapshot?.let {
                    onSuccess(it.documents.mapNotNull { document ->
                        ArticleModel(
                            id = document?.getString("id") ?: "",
                            title = document?.getString("title") ?: "",
                            content = document?.getString("content") ?: "",
                            category = document?.getString("category") ?: "",
                            date = document?.getString("date") ?: "",
                            author = document?.getString("author") ?: ""
                        )
                    })
                    return@addSnapshotListener
                }
            }
    }

    fun getArticleById(
        id: String,
        onSuccess: (ArticleModel) -> Unit,
        onFailed: (Exception) -> Unit
    ) {
        firestore
            .collection("article")
            .document(id)
            .addSnapshotListener { value, error ->
                if (error != null) {
                    onFailed(error)
                }
                value?.let { document ->
                    onSuccess(
                        ArticleModel(
                            id = document.getString("id") ?: "",
                            title = document.getString("title") ?: "",
                            content = document.getString("content") ?: "",
                            category = document.getString("category") ?: "",
                            date = document.getString("date") ?: "",
                            author = document.getString("author") ?: "",
                        )
                    )
                    return@addSnapshotListener
                }
            }
    }

    fun addForum(
        isi: String,
        onSuccess: () -> Unit,
        onFailure: (Exception) -> Unit
    ) {
        val forumId = firestore.collection("forum").document().id
        val forumData = mapOf(
            "id" to forumId,
            "isi" to isi
        )

        firestore.collection("forum")
            .document(forumId)
            .set(forumData)
            .addOnSuccessListener {
                onSuccess()
            }
            .addOnFailureListener { exception ->
                onFailure(exception)
            }
    }


    fun getForumById(
        id: String,
        onSuccess: (ForumModel) -> Unit,
        onFailed: (Exception) -> Unit
    ) {
        firestore
            .collection("forum")
            .document(id)
            .addSnapshotListener { value, error ->
                if (error != null) {
                    onFailed(error)
                }
                value?.let { document ->
                    onSuccess(
                        ForumModel(
                            id = document.getString("id") ?: "",
                            isi = document.getString("isi") ?: "",
                        )
                    )
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