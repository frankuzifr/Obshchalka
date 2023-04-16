package com.frankuzi.obshchalka.messages.presentation.messages_list

import android.util.Log
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.ViewModel
import com.frankuzi.obshchalka.messages.data.repository.MessagesRepositoryImpl
import com.frankuzi.obshchalka.messages.domain.model.Message
import com.frankuzi.obshchalka.messages.domain.use_case.GetMessagesUseCase
import com.frankuzi.obshchalka.messages.domain.use_case.MessagesUseCases
import com.frankuzi.obshchalka.messages.domain.use_case.SendMessageUseCase
import com.google.firebase.database.DataSnapshot
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class MessagesViewModel : ViewModel() {
    private var _messages = MutableStateFlow(listOf<Message>())
    val messages = _messages.asStateFlow()

    private val _useCases: MessagesUseCases

    init {
        val repository = MessagesRepositoryImpl()
        _useCases = MessagesUseCases(
            GetMessagesUseCase(repository),
            SendMessageUseCase(repository)
        )
    }

    fun updateMessages(snapshot: DataSnapshot) {
        Log.i("Message", "Update")
        val messageList = _useCases.getMessagesUseCase.getMessages(snapshot)
        _messages.update { messageList }
    }

    fun sendMessage(message: Message) {
        _useCases.sendMessageUseCase.sendMessage(message)
    }
}