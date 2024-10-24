package com.simply.birthdayapp.main.navigation

import kotlinx.serialization.Serializable

//sealed interface BottomNavBarDestination {
//    @Serializable
//    data object HomeDestination : BottomNavBarDestination
//
//    @Serializable
//    data object ShopDestination : BottomNavBarDestination
//
//    @Serializable
//    data object AddEventDestination : BottomNavBarDestination
//
//    @Serializable
//    data object ProfileDestination : BottomNavBarDestination
//}

sealed class BottomNavBarDestination(val route: String) {
    data object HomeDestination : BottomNavBarDestination("home_screen")
    data object ShopDestination : BottomNavBarDestination("shop_screen")
    data object AddEventDestination : BottomNavBarDestination("add_event_screen")
    data object ProfileDestination : BottomNavBarDestination("profile_screen")
}