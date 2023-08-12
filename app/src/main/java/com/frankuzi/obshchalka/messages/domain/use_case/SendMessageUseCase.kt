package com.frankuzi.obshchalka.messages.domain.use_case

import com.frankuzi.obshchalka.messages.domain.model.Message
import com.frankuzi.obshchalka.messages.domain.repository.MessagesRepository
import java.util.TimeZone

class SendMessageUseCase(
    private val repository: MessagesRepository
) {
    fun sendMessage(message: Message) {
        val rawOffset = TimeZone.getDefault().rawOffset.toLong()
        message.date -= rawOffset
        repository.sendMessage(message)
    }
}