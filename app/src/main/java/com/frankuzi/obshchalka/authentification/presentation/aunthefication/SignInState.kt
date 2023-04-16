package com.frankuzi.obshchalka.authentification.presentation.aunthefication

data class SignInState(
    val isSignInSuccessful: Boolean = false,
    val signInError: String? = null
)
