package com.simply.birthdayapp.commondomain.model

import com.simply.birthdayapp.R
import com.simply.birthdayapp.main.navigation.BirthdayMode
import com.simply.birthdayapp.main.navigation.BottomNavBarDestination

sealed class BottomNavItem(val iconId: Int, val route: BottomNavBarDestination) {
    data object HomeScreen :
        BottomNavItem(
            iconId = R.drawable.home_icon,
            route = BottomNavBarDestination.HomeDestination
        )

    data object ShopScreen :
        BottomNavItem(
            iconId = R.drawable.shop_icon,
            route = BottomNavBarDestination.ShopDestination
        )

    data object AddEventScreen :
        BottomNavItem(
            iconId = R.drawable.add_icon,
            route = BottomNavBarDestination.AddEventDestination/*(BirthdayMode.Add(Birthday.default))*/
        )

    data object ProfileScreen :
        BottomNavItem(
            iconId = R.drawable.profile_icon,
            route = BottomNavBarDestination.ProfileDestination
        )
}