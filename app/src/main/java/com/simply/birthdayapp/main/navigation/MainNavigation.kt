package com.simply.birthdayapp.main.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.simply.birthdayapp.auth.navigation.Destination
import com.simply.birthdayapp.main.addEvent.presentation.AddEventScreen
import com.simply.birthdayapp.main.home.presentation.HomeScreen
import com.simply.birthdayapp.main.profile.presentation.ProfileScreen
import com.simply.birthdayapp.main.shop.presentation.ShopScreen

//@Composable
//fun MainNavigation(
//    modifier: Modifier = Modifier, navController: NavHostController,
//    startDestination: BottomNavBarDestination,
//) {
//    NavHost(
//        modifier = modifier, navController = navController, startDestination = startDestination
//    ) {
//        composable<BottomNavBarDestination.HomeDestination> {
//            HomeScreen(modifier)
//        }
//        composable<BottomNavBarDestination.ShopDestination> {
//            ShopScreen(modifier)
//        }
//        composable<BottomNavBarDestination.AddEventDestination> {
//            AddEventScreen(modifier)
//        }
//        composable<BottomNavBarDestination.ProfileDestination> {
//            ProfileScreen(modifier)
//        }
//    }
//}

@Composable
fun MainNavigation(
    modifier: Modifier = Modifier, navController: NavHostController,
    startDestination: BottomNavBarDestination
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination.route // Use the route string
    ) {
        composable(BottomNavBarDestination.HomeDestination.route) {
            HomeScreen(modifier)
        }
        composable(BottomNavBarDestination.ShopDestination.route) {
            ShopScreen(modifier)
        }
        composable(BottomNavBarDestination.AddEventDestination.route) {
            AddEventScreen(modifier)
        }
        composable(BottomNavBarDestination.ProfileDestination.route) {
            ProfileScreen(modifier)
        }
    }
}