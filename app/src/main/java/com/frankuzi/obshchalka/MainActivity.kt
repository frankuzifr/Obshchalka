package com.frankuzi.obshchalka

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.ui.Modifier
import com.frankuzi.obshchalka.messages.data.data_source.FirebaseDatabaseSource
import com.frankuzi.obshchalka.messages.presentation.messages_list.MessagesScreen
import com.frankuzi.obshchalka.messages.presentation.messages_list.MessagesViewModel
import com.frankuzi.obshchalka.ui.theme.ObshchalkaTheme
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class MainActivity : ComponentActivity() {
    private val viewModel = MessagesViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ObshchalkaTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    MessagesScreen(viewModel)
                }
            }
        }

        onMessagesChanged()
    }

    private fun onMessagesChanged() {
        val reference = FirebaseDatabaseSource.reference

        reference.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                viewModel.updateMessages()
            }

            override fun onCancelled(error: DatabaseError) {

            }
        })
    }
}