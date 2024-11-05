package com.simply.birthdayapp.commonpresentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.simply.birthdayapp.auth.authnavigation.presentation.auth.AuthScreen
import com.simply.birthdayapp.main.MainScreen

@Composable
fun AppNavigation(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    startDestination: AppDestination,
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination,
    ) {
        composable<AppDestination.AuthDestination> {
            AuthScreen(modifier = modifier, navigateToMain = {
                navController.navigate(AppDestination.MainDestination)
            })
        }

        composable<AppDestination.MainDestination> {
            MainScreen(
                navigateToLogin = {
                navController.navigate(AppDestination.AuthDestination)
            },
                navigateToMain = {
                    navController.navigate(AppDestination.MainDestination)
                })
        }
    }
}