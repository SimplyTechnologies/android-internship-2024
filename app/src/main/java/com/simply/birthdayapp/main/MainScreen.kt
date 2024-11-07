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
import androidx.navigation.toRoute
import com.simply.birthdayapp.commondomain.model.Birthday
import com.simply.birthdayapp.commonpresentation.navigation.BottomNavigationBar
import com.simply.birthdayapp.commonpresentation.theme.AppBackgroundColor
import com.simply.birthdayapp.main.addEvent.presentation.AddEventScreen
import com.simply.birthdayapp.main.home.navigation.BirthdayModeNavType
import com.simply.birthdayapp.main.home.navigation.HomeNavType
import com.simply.birthdayapp.main.home.presentation.screens.HomeMainScreen
import com.simply.birthdayapp.main.navigation.BirthdayMode
import com.simply.birthdayapp.main.navigation.BottomNavBarDestination
import com.simply.birthdayapp.main.profile.navigation.ProfileMainScreen
import com.simply.birthdayapp.main.shop.presentation.screens.ShopMainScreen
import kotlin.reflect.typeOf

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen(
    modifier: Modifier = Modifier, navigateToLogin: () -> Unit = {}, navigateToMain: () -> Unit = {}
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
                HomeMainScreen() { mode ->
                    navController.navigate(BottomNavBarDestination.AddEventDestination(BirthdayMode.Edit(mode.birthday)))
                }
            }
            composable<BottomNavBarDestination.ShopDestination> {
                ShopMainScreen()
            }
            composable<BottomNavBarDestination.AddEventDestination>(
                typeMap = mapOf(
                    typeOf<Birthday>() to HomeNavType.HomeDomainType,
                    typeOf<BirthdayMode>() to BirthdayModeNavType.BirthdayModeType
                )
            ) {
                val eventMode = it.toRoute<BottomNavBarDestination.AddEventDestination>().eventMode
                AddEventScreen(navigateToMain = navigateToMain, birthdayMode = eventMode, navigateToDetails = {
                    navController.navigateUp()
                })
            }
            composable<BottomNavBarDestination.ProfileDestination> {
                ProfileMainScreen(navigateToLogin)
            }
        }
    }
}