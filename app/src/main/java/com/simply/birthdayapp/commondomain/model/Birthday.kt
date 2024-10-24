package com.simply.birthdayapp.commondomain.model

import java.time.LocalDateTime

data class Birthday(
    val createdAt: LocalDateTime,
    val date: LocalDateTime,
    val id: Int,
    val image: String?,
    val message: String?,
    val name: String,
    val relation: String,
    val upcomingAge: Int?,
    val upcomingBirthday: LocalDateTime,
    val updatedAt: LocalDateTime,
    val userId: Int
)