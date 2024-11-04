package com.simply.birthdayapp.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.simply.birthdayapp.commonpresentation.navigation.BottomNavigationBar
import com.simply.birthdayapp.commonpresentation.theme.AppBackgroundColor
import com.simply.birthdayapp.main.addEvent.presentation.AddEventScreen
import com.simply.birthdayapp.main.home.presentation.HomeScreen
import com.simply.birthdayapp.main.navigation.BottomNavBarDestination
import com.simply.birthdayapp.main.profile.navigation.ProfileMainScreen
import com.simply.birthdayapp.main.shop.presentation.screens.ShopMainScreen

@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    navigateToLogin: () -> Unit = {},
) {
    val navController = rememberNavController()

    Scaffold(modifier = Modifier.fillMaxSize().background(AppBackgroundColor), bottomBar = {
        BottomNavigationBar(navController)
    }) { innerPadding ->
        NavHost(
            modifier = modifier
                .fillMaxSize()
                .background(AppBackgroundColor)
                .padding(innerPadding),
            navController = navController,
            startDestination = BottomNavBarDestination.HomeDestination
        ) {
            composable<BottomNavBarDestination.HomeDestination> {
                HomeScreen()
            }
            composable<BottomNavBarDestination.ShopDestination> {
                ShopMainScreen()
            }
            composable<BottomNavBarDestination.AddEventDestination> {
                AddEventScreen()
            }
            composable<BottomNavBarDestination.ProfileDestination> {
                ProfileMainScreen(navigateToLogin)
            }
        }
    }
}