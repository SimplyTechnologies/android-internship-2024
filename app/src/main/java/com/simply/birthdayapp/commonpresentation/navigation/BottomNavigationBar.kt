package com.simply.birthdayapp.commonpresentation.navigation

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.simply.birthdayapp.R
import com.simply.birthdayapp.commonpresentation.theme.BottomNavActiveItemColor
import com.simply.birthdayapp.commonpresentation.theme.BottomNavBarColor
import com.simply.birthdayapp.commonpresentation.theme.BottomNavDisableItemColor
import com.simply.birthdayapp.main.navigation.BottomNavBarDestination

@Composable
fun BottomNavigationBar(navController: NavController) {
    val items = listOf(
        BottomNavItem.HomeScreen,
        BottomNavItem.ShopScreen,
        BottomNavItem.AddPersonScreen,
        BottomNavItem.ProfileScreen
    )

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    BottomNavigation(backgroundColor = BottomNavBarColor) {
        items.forEach { item ->
            val isSelected = item.route::class.qualifiedName == currentDestination?.route
            BottomNavigationItem(
                icon = {
                    Icon(
                        painter = painterResource(item.iconId),
                        contentDescription = null,
                        tint = if (isSelected) BottomNavActiveItemColor else BottomNavDisableItemColor
                    )
                },
                selected = isSelected,
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

sealed class BottomNavItem(val iconId: Int, val route: BottomNavBarDestination) {
    data object HomeScreen :
        BottomNavItem(R.drawable.home_icon, BottomNavBarDestination.HomeDestination)

    data object ShopScreen :
        BottomNavItem(R.drawable.shop_icon, BottomNavBarDestination.ShopDestination)

    data object AddPersonScreen :
        BottomNavItem(R.drawable.add_icon, BottomNavBarDestination.AddEventDestination)

    data object ProfileScreen :
        BottomNavItem(R.drawable.profile_icon, BottomNavBarDestination.ProfileDestination)
}