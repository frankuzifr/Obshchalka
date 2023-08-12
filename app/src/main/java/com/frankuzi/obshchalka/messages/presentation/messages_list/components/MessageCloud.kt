package com.frankuzi.obshchalka.messages.presentation.messages_list.components

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.frankuzi.obshchalka.App
import com.frankuzi.obshchalka.messages.domain.model.Message
import com.frankuzi.obshchalka.ui.theme.Cyan800
import com.frankuzi.obshchalka.ui.theme.CyanA700
import com.frankuzi.obshchalka.ui.theme.Teal800
import com.frankuzi.obshchalka.ui.theme.TealA700
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun MessageCloud(
    message: Message,
    modifier: Modifier
) {

    message.senderEmail?.let {
        val appInstance = App.instance
        val signedInUser = appInstance.googleAuthUIClient.getSignedInUser()

        if (it == signedInUser?.email)
            YourMessageCloud(message, modifier)
        else
            OpponentMessageCloud(message, modifier)
    }
}

@SuppressLint("SimpleDateFormat")
@Composable
fun YourMessageCloud(
    message: Message,
    modifier: Modifier
) {
    val formatter = SimpleDateFormat("dd.MM.yyyy HH:mm")
    val dateText: String = formatter.format(Date(message.date))

    Box(
        modifier = modifier
            .padding(5.dp)
            .padding(start = 100.dp)
    )
    {
        val isSystemInDarkTheme = isSystemInDarkTheme()
        Box(
            modifier = Modifier
                .background(
                    color = if (isSystemInDarkTheme) Teal800 else TealA700,
                    shape = RoundedCornerShape(8.dp)
                )
                .align(Alignment.CenterEnd)
        ) {
            Column(
                modifier = Modifier
                    .padding(start = 10.dp, end = 10.dp, top = 5.dp, bottom = 5.dp),
                horizontalAlignment = Alignment.End
            ) {
                NameText(
                    name = message.name ?: "Anonymous",
                    modifier = Modifier
                )
                Spacer(modifier = Modifier.height(5.dp))
                MessageText(
                    messageText = message.messageText ?: "",
                    modifier = Modifier
                )
                Spacer(modifier = Modifier.height(5.dp))
                DateText(
                    dateText = dateText,
                    modifier = Modifier
                )
            }
        }
    }
}

@SuppressLint("SimpleDateFormat")
@Composable
fun OpponentMessageCloud(
    message: Message,
    modifier: Modifier
) {
    val formatter = SimpleDateFormat("dd.MM.yyyy HH:mm")
    val dateText: String = formatter.format(Date(message.date))

    val isSystemInDarkTheme = isSystemInDarkTheme()
    Box(
        modifier = modifier
            .padding(5.dp)
            .padding(end = 100.dp)
    )
    {
        Box(
            modifier = Modifier
                .background(
                    color = if (isSystemInDarkTheme) Cyan800 else CyanA700,
                    shape = RoundedCornerShape(8.dp)
                )
                .align(Alignment.CenterStart)
        ) {
            Column(
                modifier = Modifier
                    .padding(start = 10.dp, end = 10.dp, top = 5.dp, bottom = 5.dp),
                horizontalAlignment = Alignment.Start
            ) {
                NameText(
                    name = message.name ?: "Anonymous",
                    modifier = Modifier
                )
                Spacer(modifier = Modifier.height(5.dp))
                MessageText(
                    messageText = message.messageText ?: "",
                    modifier = Modifier
                )
                Spacer(modifier = Modifier.height(5.dp))
                DateText(
                    dateText = dateText,
                    modifier = Modifier
                )
            }
        }
    }
}

@Composable
fun NameText(
    name: String,
    modifier: Modifier
) {
    Text(
        text = name,
        modifier = modifier,
        fontSize = 12.sp,
        fontStyle = FontStyle.Italic
    )
}

@Composable
fun MessageText(
    messageText: String,
    modifier: Modifier
) {
    Text(
        text = messageText,
        modifier = modifier,
        fontSize = 16.sp
    )
}

@Composable
fun DateText(
    dateText: String,
    modifier: Modifier
) {
    Text(
        text = dateText,
        modifier = modifier,
        fontSize = 10.sp
    )
}

@Preview
@Composable
fun Preview() {
    Column(modifier = Modifier.fillMaxSize()) {
        MessageCloud(
            message = Message(
                name = "Oleg",
                messageText = "Helloasdasdasdasda alksjd;lkasj sdfsdf asdas asdasdaaaa lKSDJ Lksjd lK",
                date = 10000L
            ),
            modifier = Modifier
                .fillMaxWidth()
        )
        //Spacer(modifier = Modifier.height(5.dp))
        MessageCloud(
            message = Message(
                name = "Andrew",
                messageText = "ASldkjasdkjlkadfjnca ajdkfaksdj aksdjfa kndaidf akjsdnc aksdf akjndc kajsndfia lskdcjn ",
                date = 10000L
            ),
            modifier = Modifier
                .fillMaxWidth()
        )
    }
}