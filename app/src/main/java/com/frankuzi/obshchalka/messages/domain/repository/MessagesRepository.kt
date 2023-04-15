package com.frankuzi.obshchalka.messages.domain.repository

import com.frankuzi.obshchalka.messages.domain.model.Message

interface MessagesRepository {
    fun getMessages(): List<Message>
    fun sendMessage(message: Message)
}