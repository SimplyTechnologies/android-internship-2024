package com.simply.birthdayapp.auth.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.simply.birthdayapp.auth.authnavigation.presentation.home.HomeScreen
import com.simply.birthdayapp.auth.landing.presentation.LandingScreen
import com.simply.birthdayapp.auth.signIn.presentation.SignInScreen

@Composable
fun MainNavigation(
    modifier: Modifier = Modifier, navController: NavHostController,
     startDestination: Destination,
) {

    NavHost(
        modifier = modifier, navController = navController, startDestination = startDestination
    ) {

        composable<Destination.HomeDestination> {
            HomeScreen(modifier)
        }
    }
}