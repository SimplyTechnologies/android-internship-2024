package com.simply.birthdayapp.commondomain.model

data class Birthday(
    val createdAt: String,
    val date: String,
    val id: Int,
    val image: String?,
    val message: String?,
    val name: String,
    val relation: String,
    val upcomingAge: Int?,
    val upcomingBirthday: String,
    val updatedAt: String,
    val userId: Int
)