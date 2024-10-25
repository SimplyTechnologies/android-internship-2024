package com.simply.birthdayapp.commondomain.model

import com.simply.birthdayapp.R
import com.simply.birthdayapp.main.navigation.BottomNavBarDestination

sealed class BottomNavItem(val iconId: Int, val route: BottomNavBarDestination) {
    data object HomeScreen :
        BottomNavItem(R.drawable.home_icon, BottomNavBarDestination.HomeDestination)

    data object ShopScreen :
        BottomNavItem(R.drawable.shop_icon, BottomNavBarDestination.ShopDestination)

    data object AddEventScreen :
        BottomNavItem(R.drawable.add_icon, BottomNavBarDestination.AddEventDestination)

    data object ProfileScreen :
        BottomNavItem(R.drawable.profile_icon, BottomNavBarDestination.ProfileDestination)
}