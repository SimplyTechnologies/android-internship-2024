package com.simply.birthdayapp.main.profile.navigation

import com.simply.birthdayapp.main.profile.profile.domain.model.UserDomain
import kotlinx.serialization.Serializable

@Serializable
sealed class ProfileDestination {
    @Serializable
    data class EditAccountScreen(val user: UserDomain) : ProfileDestination()

    @Serializable
    data object ChangePasswordScreen : ProfileDestination()

    @Serializable
    data object ProfileScreen : ProfileDestination()
}
