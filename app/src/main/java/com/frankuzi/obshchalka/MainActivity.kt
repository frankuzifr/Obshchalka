package com.frankuzi.obshchalka

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.frankuzi.obshchalka.authentification.presentation.aunthefication.SignInScreen
import com.frankuzi.obshchalka.authentification.presentation.aunthefication.SignInViewModel
import com.frankuzi.obshchalka.messages.data.data_source.FirebaseDatabaseSource
import com.frankuzi.obshchalka.messages.domain.model.Message
import com.frankuzi.obshchalka.messages.presentation.messages_list.MessagesScreen
import com.frankuzi.obshchalka.messages.presentation.messages_list.MessagesViewModel
import com.frankuzi.obshchalka.ui.theme.Gray900
import com.frankuzi.obshchalka.ui.theme.ObshchalkaTheme
import com.frankuzi.obshchalka.ui.theme.Red500
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.launch
import java.util.*

class MainActivity : ComponentActivity() {
    private val messagesViewModel = MessagesViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val context: Context = this
        setContent {
            ObshchalkaTheme {
                val systemUiController = rememberSystemUiController()
                val isSystemInDarkTheme = isSystemInDarkTheme()

                DisposableEffect(systemUiController, isSystemInDarkTheme) {
                    if (isSystemInDarkTheme) {
                        systemUiController.setSystemBarsColor(
                            color = Gray900,
                            darkIcons = false
                        )
                    } else {
                        systemUiController.setSystemBarsColor(
                            color = Red500,
                            darkIcons = true
                        )
                    }

                    onDispose {}
                }
                val openDialog = remember { mutableStateOf(false) }

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val navController = rememberNavController()
                    NavHost(navController = navController, startDestination = "sign_in") {
                        composable("sign_in") {
                            val signInViewModel = viewModel<SignInViewModel>()
                            val state by signInViewModel.state.collectAsStateWithLifecycle()

                            LaunchedEffect(key1 = Unit) {
                                if (App.instance.googleAuthUIClient.getSignedInUser() != null) {
                                    navController.navigate("messages") {
                                        popUpTo("sign_in") {
                                            inclusive = true
                                        }
                                    }
                                }
                            }
                            
                            val launcher = rememberLauncherForActivityResult(
                                contract = ActivityResultContracts.StartIntentSenderForResult(),
                                onResult = { result ->
                                    if (result.resultCode == RESULT_OK) {
                                        lifecycleScope.launch {
                                            val signInResult = App.instance.googleAuthUIClient.signInWithIntent(
                                                intent = result.data ?: return@launch
                                            )
                                            signInViewModel.onSignInResult(signInResult)
                                        }
                                    }
                                }
                            )

                            LaunchedEffect(key1 = state.isSignInSuccessful) {
                                if (state.isSignInSuccessful) {
                                    Toast.makeText(
                                        context,
                                        context.getText(R.string.sign_in_successful),
                                        Toast.LENGTH_LONG
                                    ).show()

                                    navController.navigate("messages") {
                                        popUpTo("sign_in") {
                                            inclusive = true
                                        }
                                    }
                                    signInViewModel.resetState()
                                }
                            }
                            
                            SignInScreen(
                                state = state,
                                onSignInClick = {
                                    lifecycleScope.launch {
                                        val signInIntentSender = App.instance.googleAuthUIClient.signIn()
                                        launcher.launch(
                                            IntentSenderRequest.Builder(
                                                signInIntentSender ?: return@launch
                                            ).build()
                                        )
                                    }
                                }
                            ) 
                        }
                        composable("messages") {
                            MessagesScreen(
                                onSendMessageClick = { messageText ->
                                    messagesViewModel.sendMessage(Message(
                                        name = App.instance.googleAuthUIClient.getSignedInUser()?.userName ?: "Anonymous",
                                        messageText = messageText,
                                        senderEmail = App.instance.googleAuthUIClient.getSignedInUser()?.email,
                                        date = Calendar.getInstance().time.time
                                    ))
                                },
                                onSignOutClick = {
                                    openDialog.value = true
                                },
                                viewModel = messagesViewModel
                            )
                        }
                    }

                    if (openDialog.value) {
                        AlertDialog(
                            shape = RoundedCornerShape(8.dp),
                            onDismissRequest = {
                                openDialog.value = false
                            },
                            confirmButton = {
                                Button(
                                    colors = ButtonDefaults.buttonColors(
                                        backgroundColor = MaterialTheme.colors.surface
                                    ),
                                    onClick = {
                                        lifecycleScope.launch {
                                            App.instance.googleAuthUIClient.signOut()
                                            Toast.makeText(
                                                context,
                                                context.getText(R.string.signed_out),
                                                Toast.LENGTH_LONG
                                            ).show()
                                            openDialog.value = false
                                            navController.navigate("sign_in") {
                                                popUpTo("messages") {
                                                    inclusive = true
                                                }
                                            }
                                        }
                                    },
                                    elevation = ButtonDefaults.elevation(0.dp)
                                ) {
                                    Text(text = context.getString(R.string.yes))
                                }
                            },
                            dismissButton = {
                                Button(
                                    colors = ButtonDefaults.buttonColors(
                                        backgroundColor = MaterialTheme.colors.surface
                                    ),
                                    onClick = {
                                        openDialog.value = false
                                    },
                                    elevation = ButtonDefaults.elevation(0.dp)
                                ) {
                                    Text(text = context.getString(R.string.no))
                                }
                            },
                            title = {
                                Text(text = context.getString(R.string.sign_out))
                            },
                            text = {
                                Text(text = context.getString(R.string.you_want_to_log_out))
                            }
                        )
                    }
                }
            }
        }

        onMessagesChanged()
    }

    private fun onMessagesChanged() {
        val reference = FirebaseDatabaseSource.reference

        reference.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                messagesViewModel.updateMessages(snapshot)
            }

            override fun onCancelled(error: DatabaseError) {

            }
        })
    }
}