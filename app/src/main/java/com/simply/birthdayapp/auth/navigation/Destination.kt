package com.simply.birthdayapp.auth.navigation

import kotlinx.serialization.Serializable

sealed interface Destination {
    @Serializable
    data object SignInDestination : Destination

    @Serializable
    data object RegistrationDestination : Destination

    @Serializable
    data object LandingDestination : Destination

}