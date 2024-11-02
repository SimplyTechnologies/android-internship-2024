package com.simply.birthdayapp.main.addEvent.domain.model

import java.util.Date

data class CreateBirthdayInputDomain(
    val date: Date,
    val image: String?,
    val message: String?,
    val name: String,
    val relation: String
)
