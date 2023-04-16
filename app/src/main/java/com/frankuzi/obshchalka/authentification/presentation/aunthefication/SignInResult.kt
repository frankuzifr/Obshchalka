package com.frankuzi.obshchalka.authentification.presentation.aunthefication

data class SignInResult(
    val data: UserData?,
    val errorMessage: String?
)

data class UserData(
    val userId: String,
    val email: String?,
    val userName: String?,
    val profilePictureUrl: String?
)
