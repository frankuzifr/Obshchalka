package com.frankuzi.obshchalka.messages.domain.model

import java.util.*

data class Message(
    val name: String? = null,
    val messageText: String? = null,
    val date: Date? = null,
    val senderEmail: String? = null
)