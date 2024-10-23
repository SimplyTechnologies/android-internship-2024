package com.simply.birthdayapp.commonpresentation.navigation

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import com.simply.birthdayapp.R

@Composable
fun BottomNavigationBar(navController: NavController) {
    val items = listOf(
        BottomNavItem.HomeScreen,
        BottomNavItem.ShopScreen,
        BottomNavItem.AddPersonScreen,
        BottomNavItem.ProfileScreen
    )
    BottomNavigation {
        val currentRoute = navController.currentDestination?.route
        items.forEach { item ->
            BottomNavigationItem(
                icon = {
                    Icon(painterResource(item.iconId), contentDescription = null)
                },
                selected = currentRoute == item.route,
                onClick = {
                    navController.navigate(item.route) {
                        popUpTo(navController.graph.startDestinationId)
                        launchSingleTop = true
                    }
                }
            )
        }
    }

}

sealed class BottomNavItem(val iconId: Int, val route: String) {
    object HomeScreen : BottomNavItem(R.drawable.home_icon, "home_screen")
    object ShopScreen : BottomNavItem(R.drawable.shop_icon, "shop_screen")
    object AddPersonScreen : BottomNavItem(R.drawable.add_icon, "Add_person_screen")
    object ProfileScreen : BottomNavItem(R.drawable.profile_icon, "profile_screen")
}