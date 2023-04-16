package com.frankuzi.obshchalka.messages.domain.repository

import com.frankuzi.obshchalka.messages.domain.model.Message
import com.google.firebase.database.DataSnapshot

interface MessagesRepository {
    fun getMessages(snapshot: DataSnapshot): List<Message>
    fun sendMessage(message: Message)
}