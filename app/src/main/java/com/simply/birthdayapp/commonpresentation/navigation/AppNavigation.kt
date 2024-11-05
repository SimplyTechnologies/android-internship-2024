package com.simply.birthdayapp.commonpresentation.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.simply.birthdayapp.auth.authnavigation.presentation.auth.AuthScreen
import com.simply.birthdayapp.commonpresentation.theme.AppBackgroundColor
import com.simply.birthdayapp.main.MainScreen

@Composable
fun AppNavigation(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    startDestination: AppDestination,
) {
    Scaffold(
        Modifier
            .fillMaxSize()
            .background(AppBackgroundColor)
    ) { innerPadding ->
        NavHost(
            modifier = Modifier
                .background(AppBackgroundColor)
                .padding(innerPadding),
            navController = navController,
            startDestination = startDestination,
        ) {
            composable<AppDestination.AuthDestination> {
                AuthScreen(modifier = modifier.background(AppBackgroundColor), navigateToMain = {
                    navController.navigate(AppDestination.MainDestination)
                })
            }

            composable<AppDestination.MainDestination> {
                MainScreen(navigateToLogin = {
                    navController.navigate(AppDestination.AuthDestination)
                }, navigateToMain = {
                    navController.navigate(AppDestination.MainDestination)
                })
            }
        }
    }
}