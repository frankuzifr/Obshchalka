package com.frankuzi.obshchalka.messages.domain.use_case

import com.frankuzi.obshchalka.messages.domain.model.Message
import com.frankuzi.obshchalka.messages.domain.repository.MessagesRepository

class SendMessageUseCase(
    private val repository: MessagesRepository
) {
    fun sendMessage(message: Message) {
        repository.sendMessage(message)
    }
}