package com.frankuzi.obshchalka.messages.domain.model

import java.util.*

data class Message(
    val name: String? = null,
    val messageText: String? = null,
    var date: Long = 0,
    val senderEmail: String? = null
)