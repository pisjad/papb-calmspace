package com.chrisp.calmspace.feature.konsultasi

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel

class ChatViewModel : ViewModel() {
    private val _messages = mutableStateListOf<Message>()
    val messages: List<Message> = _messages


    fun sendMessage(content: String, senderId: String) {
        val message = Message(
            content = content,
            senderId = senderId
        )
        _messages.add(message)
    }


}

