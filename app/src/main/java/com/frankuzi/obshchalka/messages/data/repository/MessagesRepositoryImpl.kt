package com.frankuzi.obshchalka.messages.data.repository

import com.frankuzi.obshchalka.messages.data.data_source.FirebaseDatabaseSource
import com.frankuzi.obshchalka.messages.domain.model.Message
import com.frankuzi.obshchalka.messages.domain.repository.MessagesRepository

class MessagesRepositoryImpl : MessagesRepository {

    override fun getMessages(): List<Message>{
        return listOf()
    }

    override fun sendMessage(message: Message) {

        FirebaseDatabaseSource.reference.setValue(message.messageText)
    }
}