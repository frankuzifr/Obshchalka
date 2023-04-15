package com.frankuzi.obshchalka.messages.data.data_source

import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class FirebaseDatabaseSource {
    companion object {
        val database = Firebase.database
        val reference = database.getReference("messages")
    }
}