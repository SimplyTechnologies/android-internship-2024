package com.simply.birthdayapp.main.home.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.simply.birthdayapp.commondomain.model.Birthday
import com.simply.birthdayapp.commonpresentation.theme.AppBackgroundColor
import com.simply.birthdayapp.main.home.navigation.HomeDestination
import com.simply.birthdayapp.main.home.navigation.HomeNavType
import com.simply.birthdayapp.main.home.presentation.HomeScreen
import com.simply.birthdayapp.main.navigation.BirthdayMode
import kotlin.reflect.typeOf

@Composable
fun HomeMainScreen(
    navigateToEditBirthdayScreen: (BirthdayMode) -> Unit = {}
) {
    val homeNavController = rememberNavController()
    NavHost(
        modifier = Modifier
            .fillMaxSize()
            .background(AppBackgroundColor),
        navController = homeNavController,
        startDestination = HomeDestination.HomeScreenDestination
    ) {
        composable<HomeDestination.HomeScreenDestination> {
            HomeScreen { birthday ->
                homeNavController.navigate(HomeDestination.BirthdayDetailsDestination(birthday))
            }
        }
        composable<HomeDestination.BirthdayDetailsDestination>(
            typeMap = mapOf(typeOf<Birthday>() to HomeNavType.HomeDomainType)
        ) {
            val birthday = it.toRoute<HomeDestination.BirthdayDetailsDestination>().birthday
            BirthdayDetailsScreen(_birthday = birthday, navigateToHomeScreen = {
                homeNavController.navigateUp()
            }, navigateToEditScreen = { mode ->
                navigateToEditBirthdayScreen(mode)
            })
        }
    }
}