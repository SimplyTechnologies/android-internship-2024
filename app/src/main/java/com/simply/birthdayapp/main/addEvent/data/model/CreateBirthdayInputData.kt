package com.simply.birthdayapp.main.addEvent.data.model

import java.util.Date

data class CreateBirthdayInputData(
    val date: Date,
    val image: String?,
    val message: String?,
    val name: String,
    val relation: String
)
