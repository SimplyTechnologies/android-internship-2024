package com.simply.birthdayapp.main.navigation

import com.simply.birthdayapp.commondomain.model.Birthday
import kotlinx.serialization.Serializable

@Serializable
sealed class BirthdayMode(val birthday: Birthday) {
    @Serializable
    data class Add(val addBirthday: Birthday) : BirthdayMode(addBirthday)
    @Serializable
    data class Edit(val editBirthday: Birthday) : BirthdayMode(editBirthday)
}