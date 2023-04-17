package com.frankuzi.obshchalka.messages.presentation.messages_list

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.frankuzi.obshchalka.R
import com.frankuzi.obshchalka.messages.presentation.messages_list.components.MessageCloud
import com.frankuzi.obshchalka.messages.presentation.messages_list.components.MessageSendBlock
import com.frankuzi.obshchalka.ui.theme.Gray900

@Composable
fun MessagesScreen(
    onSendMessageClick: (String) -> Unit,
    onSignOutClick: () -> Unit,
    viewModel: MessagesViewModel
){
    val context = LocalContext.current
    val messages by viewModel.messages.collectAsStateWithLifecycle()

    var messageText by remember {
        mutableStateOf("")
    }
    val scrollState = rememberLazyListState()

    LaunchedEffect(key1 = messages.size) {
        scrollState.scrollToItem(messages.size)
    }

    Image(
        painter = painterResource(id = R.drawable.cats),
        contentDescription = "Background",
        modifier = Modifier.fillMaxSize(),
        contentScale = ContentScale.Crop,
        colorFilter = ColorFilter.tint(color = Color.Gray, blendMode = BlendMode.Darken)
    )
    Scaffold(
        topBar = {
            TopAppBar(
                backgroundColor = MaterialTheme.colors.background
            ) {
                Text(
                    text = context.getString(R.string.obshchalka),
                    fontSize = 18.sp,
                    modifier = Modifier.padding(start = 10.dp))
                Spacer(Modifier.weight(1f, true))
                IconButton(onClick = onSignOutClick) {
                    Icon(
                        painter = painterResource(id = R.drawable.baseline_exit_to_app_24),
                        contentDescription = "Logout"
                    )
                }
            }
        },
        bottomBar = {
            MessageSendBlock(
                textFieldText = messageText,
                textFieldOnValueChanged = {
                    messageText = it
                },
                textFiledPlaceholderText = context.getString(R.string.enter_message),
                sendButtonOnClick = {
                    onSendMessageClick.invoke(messageText)
                    messageText = ""
                                    },
                modifier = Modifier
            )
        },
        backgroundColor = Color.Transparent
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = it.calculateBottomPadding()),
            state = scrollState
        ) {
            items(messages){ message ->
                MessageCloud(
                    message = message,
                    modifier = Modifier
                        .fillMaxWidth())
            }
        }
    }
}

@Preview(name = "dark theme",
    group = "themes",
    uiMode = UI_MODE_NIGHT_YES)
@Composable
fun MessagesScreenPreview() {
    MessagesScreen(onSendMessageClick = {}, onSignOutClick = { /*TODO*/ }, viewModel = MessagesViewModel())
}