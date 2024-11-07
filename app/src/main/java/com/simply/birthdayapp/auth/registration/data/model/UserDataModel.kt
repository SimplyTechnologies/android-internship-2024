package com.simply.birthdayapp.auth.registration.data.model

data class UserDataModel(
    val id: Int,
    val email: String,
    val firstName: String,
    val lastName: String,
    val image: String?
)