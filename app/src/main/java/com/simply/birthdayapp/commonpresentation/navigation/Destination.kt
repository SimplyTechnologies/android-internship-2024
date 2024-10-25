package com.simply.birthdayapp.commonpresentation.navigation

import kotlinx.serialization.Serializable

sealed interface Destination {
    @Serializable
    data object AuthDestination : Destination

    @Serializable
    data object MainDestination : Destination
}
