package com.simply.birthdayapp.main.addEvent.data.model

data class BirthdayDataModel(
    val createdAt: String,
    val date: String,
    val id: Int,
    val image: String?,
    val message: String?,
    val name: String,
    val relation: String,
    val upcomingAge: Int?,
    val upcomingBirthday: String?,
    val updatedAt: String,
    val userId: Int
)
