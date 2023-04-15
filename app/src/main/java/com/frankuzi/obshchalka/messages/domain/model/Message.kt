package com.frankuzi.obshchalka.messages.domain.model

import java.util.*

data class Message(
    val name: String,
    val messageText: String,
    val date: Date,
    val isYourMessage: Boolean
)