package com.frankuzi.obshchalka.messages.domain.use_case

import com.frankuzi.obshchalka.messages.domain.model.Message
import com.frankuzi.obshchalka.messages.domain.repository.MessagesRepository
import java.util.TimeZone

class SendMessageUseCase(
    private val repository: MessagesRepository
) {
    fun sendMessage(message: Message) {
        message.date?.let { date ->
            val rawOffset = TimeZone.getDefault().rawOffset
            date.time -= rawOffset
        }
        repository.sendMessage(message)
    }
}