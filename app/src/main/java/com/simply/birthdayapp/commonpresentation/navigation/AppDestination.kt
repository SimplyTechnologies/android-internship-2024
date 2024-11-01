package com.simply.birthdayapp.commonpresentation.navigation

import kotlinx.serialization.Serializable

sealed interface AppDestination {
    @Serializable
    data object AuthDestination : AppDestination

    @Serializable
    data object MainDestination : AppDestination
}
