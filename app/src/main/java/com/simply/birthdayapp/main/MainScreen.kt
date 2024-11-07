package com.simply.birthdayapp.main

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.simply.birthdayapp.commonpresentation.navigation.BottomNavigationBar
import com.simply.birthdayapp.commonpresentation.theme.AppBackgroundColor
import com.simply.birthdayapp.main.addEvent.presentation.AddEventScreen
import com.simply.birthdayapp.main.home.navigation.HomeDestination
import com.simply.birthdayapp.main.home.presentation.HomeScreen
import com.simply.birthdayapp.main.home.presentation.screens.HomeMainScreen
import com.simply.birthdayapp.main.navigation.BottomNavBarDestination
import com.simply.birthdayapp.main.profile.navigation.ProfileMainScreen
import com.simply.birthdayapp.main.shop.presentation.screens.ShopMainScreen

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    navigateToLogin: () -> Unit = {},
    navigateToMain: () -> Unit = {}
) {
    val navController = rememberNavController()

    Scaffold(modifier = Modifier
        .fillMaxSize()
        .background(AppBackgroundColor), bottomBar = {
        BottomNavigationBar(navController)
    }) {
        NavHost(
            modifier = modifier
                .fillMaxSize()
                .navigationBarsPadding()
                .background(AppBackgroundColor),
            navController = navController,
            startDestination = BottomNavBarDestination.HomeDestination
        ) {
            composable<BottomNavBarDestination.HomeDestination> {
                HomeMainScreen()
            }
            composable<BottomNavBarDestination.ShopDestination> {
                ShopMainScreen()
            }
            composable<BottomNavBarDestination.AddEventDestination> {
                AddEventScreen(navigateToMain = navigateToMain)
            }
            composable<BottomNavBarDestination.ProfileDestination> {
                ProfileMainScreen(navigateToLogin)
            }
        }
    }
}