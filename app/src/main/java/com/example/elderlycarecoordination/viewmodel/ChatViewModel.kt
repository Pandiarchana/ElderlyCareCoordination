package com.example.elderlycarecoordination.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class ChatViewModel : ViewModel() {

    // Key: name, Value: list of messages
    private val _chatMessages = MutableStateFlow<Map<String, List<String>>>(emptyMap())
    val chatMessages: StateFlow<Map<String, List<String>>> = _chatMessages

    fun sendMessage(to: String, message: String) {
        val currentList = _chatMessages.value[to] ?: emptyList()
        _chatMessages.value = _chatMessages.value.toMutableMap().apply {
            put(to, currentList + message)
        }
    }

    fun getMessages(name: String): List<String> {
        return _chatMessages.value[name] ?: emptyList()
    }
}
