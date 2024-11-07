package com.simply.birthdayapp.main.home.navigation

import com.simply.birthdayapp.commondomain.model.Birthday
import kotlinx.serialization.Serializable

sealed interface HomeDestination {
    @Serializable
    data object HomeScreenDestination : HomeDestination

    @Serializable
    data class BirthdayDetailsDestination(val birthday: Birthday) : HomeDestination
}