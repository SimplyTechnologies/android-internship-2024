package com.simply.birthdayapp.commonpresentation.navigation

import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.simply.birthdayapp.commondomain.model.BottomNavItem
import com.simply.birthdayapp.commonpresentation.theme.BottomNavActiveItemColor
import com.simply.birthdayapp.commonpresentation.theme.BottomNavBarColor
import com.simply.birthdayapp.commonpresentation.theme.BottomNavDisableItemColor

@Composable
fun BottomNavigationBar(navController: NavController) {
    val items = listOf(
        BottomNavItem.HomeScreen,
        BottomNavItem.ShopScreen,
        BottomNavItem.AddEventScreen,
        BottomNavItem.ProfileScreen
    )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    BottomNavigation(
        modifier = Modifier.navigationBarsPadding(),
        backgroundColor = BottomNavBarColor,
    ) {
        items.forEach { item ->
            val isSelected = item.route::class.qualifiedName == currentDestination?.route
            BottomNavigationItem(icon = {
                Icon(
                    painter = painterResource(item.iconId),
                    contentDescription = null,
                    tint = if (isSelected) BottomNavActiveItemColor else BottomNavDisableItemColor
                )
            }, selected = isSelected, onClick = {
                navController.navigate(item.route) {
                    popUpTo(navController.graph.startDestinationId)
                    launchSingleTop = true
                }
            })
        }
    }
}