package com.frankuzi.obshchalka.messages.presentation.messages_list.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.frankuzi.obshchalka.R

@Composable
fun MessageSendBlock(
    textFieldText: String,
    textFieldOnValueChanged: (String) -> Unit,
    textFiledPlaceholderText: String,
    sendButtonOnClick: () -> Unit,
    modifier: Modifier
) {
    Row(modifier = modifier) {
        MessageInputField(
            textValue = textFieldText,
            onValueChanged = textFieldOnValueChanged,
            placeholderText = textFiledPlaceholderText,
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
                .weight(1f)
        )
        SendMessageButton(
            onClick = sendButtonOnClick,
            modifier = Modifier
                .width(60.dp)
                .height(60.dp),
            isInteractable = textFieldText != ""
        )
    }
}

@Composable
fun MessageInputField(
    textValue: String,
    onValueChanged: (String) -> Unit,
    placeholderText: String,
    modifier: Modifier
) {
    TextField(
        value = textValue,
        onValueChange = onValueChanged,
        modifier = modifier,
        placeholder = {
            Text(
                text = placeholderText
            )
        },
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = MaterialTheme.colors.secondary,
            cursorColor = Color.Black,
            disabledLabelColor = Color.DarkGray,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        ),
        shape = RoundedCornerShape(0.dp),
        singleLine = false,
    )
}

@Composable
fun SendMessageButton(
    onClick: () -> Unit,
    modifier: Modifier,
    isInteractable: Boolean
) {
    Button(
        onClick = onClick,
        modifier = modifier,
//        colors = ButtonDefaults.buttonColors(backgroundColor = Color.Cyan),
        shape = RoundedCornerShape(0.dp),
        enabled = isInteractable
    ) {
        Icon(
            painter = painterResource(id = R.drawable.baseline_send_24),
            contentDescription = "Send message"
        )
    }
}

@Preview
@Composable
fun MessageSendBlockPreview() {
    Column(Modifier.fillMaxSize()) {
        MessageSendBlock(
            textFieldText = "",
            textFieldOnValueChanged = {},
            textFiledPlaceholderText = "",
            sendButtonOnClick = {},
            modifier = Modifier
                .fillMaxWidth()
        )
    }
}