package com.frankuzi.obshchalka.messages.domain.use_case

data class MessagesUseCases(
    val getMessagesUseCase: GetMessagesUseCase,
    val sendMessageUseCase: SendMessageUseCase
)