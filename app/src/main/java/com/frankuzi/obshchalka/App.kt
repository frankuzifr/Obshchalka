package com.frankuzi.obshchalka

import android.app.Application
import com.frankuzi.obshchalka.authentification.presentation.aunthefication.GoogleAuthUIClient
import com.google.android.gms.auth.api.identity.Identity

class App : Application() {
    val googleAuthUIClient by lazy {
        GoogleAuthUIClient(
            context = applicationContext,
            oneTapClient = Identity.getSignInClient(applicationContext)
        )
    }

    companion object {
        lateinit var instance: App
            private set
    }

    override fun onCreate() {
        super.onCreate()

        instance = this
    }
}