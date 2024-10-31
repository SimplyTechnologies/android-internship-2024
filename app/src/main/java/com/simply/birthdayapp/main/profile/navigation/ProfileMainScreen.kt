package com.simply.birthdayapp.main.profile.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.simply.birthdayapp.main.profile.chnagepassword.presentation.ChangePasswordScreen
import com.simply.birthdayapp.main.profile.editprofile.presentation.EditMyProfileScreen
import com.simply.birthdayapp.main.profile.profile.domain.model.UserDomain
import com.simply.birthdayapp.main.profile.profile.domain.model.UserNavType
import com.simply.birthdayapp.main.profile.profile.presentation.ProfileScreen
import kotlin.reflect.typeOf

@Composable
fun ProfileMainScreen(navigateToLoginScreen: () -> Unit) {
    val navController = rememberNavController()

    NavHost(
        navController = navController, startDestination = ProfileDestination.ProfileScreen
    ) {

        composable<ProfileDestination.ProfileScreen>() {
            ProfileScreen(navigateToLogin = navigateToLoginScreen, navigateToEditAccount = {
                navController.navigate(ProfileDestination.EditAccountScreen(it))

            }, navigateToChangePassword = {
                navController.navigate(ProfileDestination.ChangePasswordScreen)
            })
        }

        composable<ProfileDestination.EditAccountScreen>(
            typeMap = mapOf(typeOf<UserDomain>() to UserNavType.UserDomainType)
        ) {
            val userDomain = it.toRoute<ProfileDestination.EditAccountScreen>().user
            EditMyProfileScreen(userDomain) {
                navController.popBackStack()
            }
        }

        composable<ProfileDestination.ChangePasswordScreen> {
            ChangePasswordScreen(navigateToLoginScreen = navigateToLoginScreen)
        }
    }
}
