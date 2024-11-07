package com.simply.birthdayapp.auth.registration.data.model

data class SignUpInputDataModel(
    val firstName: String,
    val lastName: String,
    val email: String,
    val password: String
)