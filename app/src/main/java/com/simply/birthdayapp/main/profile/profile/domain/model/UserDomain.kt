package com.simply.birthdayapp.main.profile.profile.domain.model

import kotlinx.serialization.Serializable


@Serializable
data class UserDomain(
    val id: Int,
    val email: String,
    val firstName: String,
    val lastName: String,
    val image: String?,
) {
    companion object {
        val default = UserDomain(
            id = 0,
            email = "",
            firstName = "",
            lastName = "",
            image = null,
        )
    }
}
