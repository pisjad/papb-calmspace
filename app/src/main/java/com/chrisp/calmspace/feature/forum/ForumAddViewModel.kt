package com.chrisp.calmspace.feature.forum

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chrisp.calmspace.feature.data.Repository
import kotlinx.coroutines.launch

class ForumAddViewModel : ViewModel() {
    private val repository = Repository()

    val isLoading = mutableStateOf(false)
    val errorMessage = mutableStateOf<String?>(null)
    val isSuccess = mutableStateOf(false)

    fun addForumPost(content: String) {
        if (content.isBlank()) {
            errorMessage.value = "Cerita tidak boleh kosong!"
            return
        }
        isLoading.value = true
        errorMessage.value = null

        // Launch the repository call in a coroutine

            repository.addForum(
                isi = content,
                onSuccess = {
                    isSuccess.value = true
                    isLoading.value = false
                },
                onFailure = { exception ->
                    isSuccess.value = false
                    isLoading.value = false
                    errorMessage.value = exception.message
                }
            )

    }
}
