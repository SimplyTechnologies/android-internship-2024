package com.simply.birthdayapp.commonpresentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.simply.birthdayapp.auth.authnavigation.presentation.auth.AuthScreen
import com.simply.birthdayapp.main.MainScreen

@Composable
fun AppNavigation(
    modifier: Modifier = Modifier, navController: NavHostController,
) {

    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = Destination.AuthDestination
    ) {

        composable<Destination.AuthDestination> {
            AuthScreen(
                modifier = modifier,
                navigateToMain = {
                    navController.navigate(Destination.MainDestination) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        restoreState = true
                        launchSingleTop = true
                    }
                })
        }

        composable<Destination.MainDestination> {
            MainScreen(modifier)
        }
    }
}