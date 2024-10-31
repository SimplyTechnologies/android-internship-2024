package com.simply.birthdayapp.main.navigation

import kotlinx.serialization.Serializable

sealed interface ProfileDestination {
    @Serializable
    data object ChangePasswordDestination : ProfileDestination

    @Serializable
    data object EditMyProfileDestination : ProfileDestination
}
// TODO: check and remove