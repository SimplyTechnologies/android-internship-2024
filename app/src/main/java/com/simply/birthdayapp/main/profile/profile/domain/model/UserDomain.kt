package com.simply.birthdayapp.main.profile.profile.domain.model

import kotlinx.serialization.Serializable


@Serializable
data class UserDomain(
    val id: Int = 0,
    val email: String = "",
    val firstName: String = "",
    val lastName: String = "",
    val image: String? = null,
)