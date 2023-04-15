package com.frankuzi.obshchalka.messages.domain.use_case

import com.frankuzi.obshchalka.messages.domain.model.Message
import com.frankuzi.obshchalka.messages.domain.repository.MessagesRepository

class GetMessagesUseCase(
    private val repository: MessagesRepository
) {
    fun getMessages(): List<Message> {
        return repository.getMessages()
    }
}