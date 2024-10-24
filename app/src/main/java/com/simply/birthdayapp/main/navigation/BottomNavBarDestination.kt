package com.simply.birthdayapp.main.navigation

import kotlinx.serialization.Serializable

sealed interface BottomNavBarDestination {
    @Serializable
    data object HomeDestination : BottomNavBarDestination

    @Serializable
    data object ShopDestination : BottomNavBarDestination

    @Serializable
    data object AddEventDestination : BottomNavBarDestination

    @Serializable
    data object ProfileDestination : BottomNavBarDestination

}
