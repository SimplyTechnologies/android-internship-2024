package com.simply.birthdayapp.main.profile.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.simply.birthdayapp.main.profile.chnagepassword.presentation.ChangePasswordScreen
import com.simply.birthdayapp.main.profile.editprofile.presentation.EditMyProfileScreen
import com.simply.birthdayapp.main.profile.profile.presentation.ProfileScreen

@Composable
fun ProfileMainScreen(navigateToLoginScreen: () -> Unit) {
    val navController = rememberNavController()

    NavHost(navController, startDestination = ProfileDestination.ProfileScreen) {

        composable<ProfileDestination.ProfileScreen> {
            ProfileScreen(navigateToLogin = navigateToLoginScreen, navigateToEditAccount = {
                navController.navigate(ProfileDestination.EditAccountScreen)
            }, navigateToChangePassword = {
                navController.navigate(ProfileDestination.ChangePasswordScreen)
            })
        }

        composable<ProfileDestination.EditAccountScreen> {
            EditMyProfileScreen()
        }

        composable<ProfileDestination.ChangePasswordScreen> {
            ChangePasswordScreen(navigateToLoginScreen = navigateToLoginScreen)
        }
    }
}