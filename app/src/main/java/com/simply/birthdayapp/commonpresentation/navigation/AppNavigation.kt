package com.simply.birthdayapp.commonpresentation.navigation

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.simply.birthdayapp.auth.authnavigation.presentation.auth.AuthScreen
import com.simply.birthdayapp.commonpresentation.theme.AppBackgroundColor
import com.simply.birthdayapp.main.MainScreen

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
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
    ) {
        NavHost(
            modifier = Modifier
                .background(AppBackgroundColor)
                .systemBarsPadding()
                .navigationBarsPadding(),
            navController = navController,
            startDestination = startDestination,
        ) {
            composable<AppDestination.AuthDestination> {
                AuthScreen(modifier = modifier.background(AppBackgroundColor), navigateToMain = {
                    navController.navigate(AppDestination.MainDestination) {
                        popUpTo(AppDestination.MainDestination) {
                            inclusive = true
                        }
                        launchSingleTop = true
                    }
                })
            }

            composable<AppDestination.MainDestination> {
                MainScreen(
                    navigateToLogin = {
                        if (!navController.popBackStack()) {
                            navController.navigate(AppDestination.AuthDestination)
                        }
                    },
                    navigateToMain = {
                        navController.navigate(AppDestination.MainDestination)
                    }
                )
            }
        }
    }
}