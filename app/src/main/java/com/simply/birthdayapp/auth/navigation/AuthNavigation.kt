package com.simply.birthdayapp.auth.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.simply.birthdayapp.auth.landing.presentation.LandingScreen
import com.simply.birthdayapp.auth.signIn.presentation.SignInScreen

@Composable
fun AuthNavigation(
    modifier: Modifier = Modifier, navController: NavHostController,
    navigateToMain: () -> Unit, startDestination: Destination,
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = Destination.LandingDestination
    ) {
        composable<Destination.LandingDestination> {
            LandingScreen(modifier = modifier, onSignInClick = {
                navController.navigate(Destination.SignInDestination) {
                    popUpTo(navController.graph.findStartDestination().id) {
                        saveState = true
                    }
                    restoreState = true
                    launchSingleTop = true
                }
            }, onRegisterClick = {
                // TODO: navigate to register screen
            })
        }
        composable<Destination.SignInDestination> {
            SignInScreen(modifier = modifier, navigateToMain = navigateToMain, navigateToLanding = {
                navController.navigate(Destination.LandingDestination) {
                    popUpTo(navController.graph.findStartDestination().id) {
                        saveState = true
                    }
                    restoreState = true
                }
            })
        }
    }
}