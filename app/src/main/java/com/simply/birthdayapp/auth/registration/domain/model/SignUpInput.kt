package com.simply.birthdayapp.auth.registration.domain.model

data class SignUpInput(
    val firstName: String,
    val lastName: String,
    val email: String,
    val password: String
)
