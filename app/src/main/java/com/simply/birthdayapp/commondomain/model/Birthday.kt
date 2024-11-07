package com.simply.birthdayapp.commondomain.model

import kotlinx.serialization.Serializable

@Serializable
data class Birthday(
    val createdAt: String = "",
    val date: String = "",
    val id: Int = 0,
    val image: String? = null,
    val message: String? = null,
    val name: String = "",
    val relation: String = "",
    val upcomingAge: Int? = null,
    val upcomingBirthday: String? = null,
    val updatedAt: String = "",
    val userId: Int = 0
)