package com.frankuzi.obshchalka.messages.presentation.messages_list.components

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.frankuzi.obshchalka.messages.domain.model.Message
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun MessageCloud(
    message: Message,
    modifier: Modifier
) {
    if (message.isYourMessage)
        YourMessageCloud(message, modifier)
    else
        OpponentMessageCloud(message, modifier)
}

@SuppressLint("SimpleDateFormat")
@Composable
fun YourMessageCloud(
    message: Message,
    modifier: Modifier
) {
    val formatter = SimpleDateFormat("dd.MM.yyyy HH:mm")
    val dateText: String = formatter.format(message.date)

    Box(
        modifier = modifier
            .padding(5.dp)
            .padding(start = 100.dp)
    )
    {
        Box(
            modifier = Modifier
                .background(
                    color = Color.Blue,
                    shape = RoundedCornerShape(8.dp)
                )
                .align(Alignment.CenterEnd)
        ) {
            Column(
                modifier = Modifier
                    .padding(10.dp),
                horizontalAlignment = Alignment.End
            ) {
                NameText(
                    name = message.name,
                    modifier = Modifier
                )
                MessageText(
                    messageText = message.messageText,
                    modifier = Modifier
                        .background(
                            color = Color.Blue,
                            shape = RoundedCornerShape(8.dp)
                        )
                )
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
    val dateText: String = formatter.format(message.date)

    Box(
        modifier = modifier
            .padding(5.dp)
            .padding(end = 100.dp)
    )
    {
        Box(
            modifier = Modifier
                .background(
                    color = Color.Red,
                    shape = RoundedCornerShape(8.dp)
                )
                .align(Alignment.CenterStart)
        ) {
            Column(
                modifier = Modifier
                    .padding(10.dp),
                horizontalAlignment = Alignment.Start
            ) {
                NameText(
                    name = message.name,
                    modifier = Modifier
                )
                MessageText(
                    messageText = message.messageText,
                    modifier = Modifier
                        .background(
                            color = Color.Red,
                            shape = RoundedCornerShape(8.dp)
                        )
                )
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
        fontSize = 12.sp
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
                date = Date(10000L),
                isYourMessage = true
            ),
            modifier = Modifier
                .fillMaxWidth()
        )
        //Spacer(modifier = Modifier.height(5.dp))
        MessageCloud(
            message = Message(
                name = "Andrew",
                messageText = "ASldkjasdkjlkadfjnca ajdkfaksdj aksdjfa kndaidf akjsdnc aksdf akjndc kajsndfia lskdcjn ",
                date = Date(10000L),
                isYourMessage = false
            ),
            modifier = Modifier
                .fillMaxWidth()
        )
    }
}