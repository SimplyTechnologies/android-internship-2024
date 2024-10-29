package com.simply.birthdayapp.main.profile.navigation

import kotlinx.serialization.Serializable

sealed class ProfileDestination {
    @Serializable
    data object ProfileScreen : ProfileDestination()

    @Serializable
    data object ChangePasswordScreen : ProfileDestination()

    @Serializable
    data object EditAccountScreen : ProfileDestination()

}