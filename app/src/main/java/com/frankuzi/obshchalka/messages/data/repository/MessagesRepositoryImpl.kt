package com.frankuzi.obshchalka.messages.data.repository

import com.frankuzi.obshchalka.messages.data.data_source.FirebaseDatabaseSource
import com.frankuzi.obshchalka.messages.domain.model.Message
import com.frankuzi.obshchalka.messages.domain.repository.MessagesRepository
import com.google.firebase.database.DataSnapshot

class MessagesRepositoryImpl : MessagesRepository {

    override fun getMessages(snapshot: DataSnapshot): List<Message> {
        val messages = mutableListOf<Message>()
        for (child in snapshot.children) {
            val value = child.getValue(Message::class.java)

            if (value != null)
                messages.add(value)
        }
        return messages
    }

    override fun sendMessage(message: Message) {
        val reference = FirebaseDatabaseSource.reference
        reference.child(reference.push().key ?: "Key").setValue(message)
    }
}