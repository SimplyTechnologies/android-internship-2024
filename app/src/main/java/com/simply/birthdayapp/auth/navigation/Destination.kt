package com.simply.birthdayapp.auth.navigation

import kotlinx.serialization.Serializable

sealed interface Destination {
    @Serializable
    data object SigninDestination : Destination

    @Serializable
    data object LandingDestination : Destination


}
