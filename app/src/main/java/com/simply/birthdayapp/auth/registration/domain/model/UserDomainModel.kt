package com.simply.birthdayapp.auth.registration.domain.model

data class UserDomainModel(
    val id: Int = 0,
    val email: String = "",
    val firstName: String = "",
    val lastName: String = "",
    val image: String? = null
)
