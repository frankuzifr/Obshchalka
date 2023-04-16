package com.frankuzi.obshchalka.messages.domain.use_case

import com.frankuzi.obshchalka.messages.domain.model.Message
import com.frankuzi.obshchalka.messages.domain.repository.MessagesRepository
import com.google.firebase.database.DataSnapshot
import java.time.LocalDateTime
import java.util.TimeZone

class GetMessagesUseCase(
    private val repository: MessagesRepository
) {
    fun getMessages(snapshot: DataSnapshot): List<Message> {
        val messages = repository.getMessages(snapshot)
        messages.map { message ->
            message.date?.let { date ->
                val rawOffset = TimeZone.getDefault().rawOffset
                date.time += rawOffset
            }
        }
        return messages
    }
}