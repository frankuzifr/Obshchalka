package com.frankuzi.obshchalka.messages.presentation.messages_list

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.frankuzi.obshchalka.messages.domain.model.Message
import com.frankuzi.obshchalka.messages.presentation.messages_list.components.MessageCloud
import com.frankuzi.obshchalka.messages.presentation.messages_list.components.MessageSendBlock
import java.util.*

@Composable
fun MessagesScreen(
    messagesViewModel: MessagesViewModel = viewModel()
){
    var messages by remember {
        mutableStateOf(
            listOf(
                Message(
                    name = "Oleg",
                    messageText = "Hello",
                    date = Date(10000L),
                    isYourMessage = true
                ),
                Message(
                    name = "Andrew",
                    messageText = "Poshel nahuy",
                    date = Date(10000L),
                    isYourMessage = false
                ),
            )
        )
    }

    var messageText by remember {
        mutableStateOf("")
    }

    Image(
        painter = painterResource(id = com.frankuzi.obshchalka.R.drawable.cats),
        contentDescription = "Background",
        modifier = Modifier.fillMaxSize(),
        contentScale = ContentScale.Crop,
        colorFilter = ColorFilter.tint(color = Color.Gray, blendMode = BlendMode.Darken)
    )
    Scaffold(
        bottomBar = {
            MessageSendBlock(
                textFieldText = messageText,
                textFieldOnValueChanged = {
                    messageText = it
                },
                textFiledPlaceholderText = "Enter message...",
                sendButtonOnClick = { messagesViewModel.sendMessage(Message(name = "Oleg", messageText = messageText, isYourMessage = true, date = Date(10000L))) },
                modifier = Modifier
            )
        },
        backgroundColor = Color.Transparent
    ) {
        it
        LazyColumn() {
            items(messages){ message ->
                MessageCloud(
                    message = message,
                    modifier = Modifier
                        .fillMaxWidth())
            }
        }
    }
}