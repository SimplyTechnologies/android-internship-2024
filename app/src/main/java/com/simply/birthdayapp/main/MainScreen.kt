package com.simply.birthdayapp.main

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.simply.birthdayapp.commonpresentation.navigation.BottomNavigationBar
import com.simply.birthdayapp.main.home.presentation.HomeScreen
import com.simply.birthdayapp.main.shop.presentation.ShopScreen
import com.simply.birthdayapp.main.addEvent.presentation.AddEventScreen
import com.simply.birthdayapp.main.navigation.BottomNavBarDestination
import com.simply.birthdayapp.main.profile.presentation.ProfileScreen
import org.koin.androidx.compose.koinViewModel

@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    viewModel: MainScreenViewModel = koinViewModel(),
) {
    val navController = rememberNavController()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            BottomNavigationBar(navController)
        }
    ) { innerPadding ->
        NavHost(
            modifier = modifier,
            navController = navController,
            startDestination = BottomNavBarDestination.HomeDestination // Use the route string
        ) {
            composable<BottomNavBarDestination.HomeDestination> {
                HomeScreen()
            }
            composable<BottomNavBarDestination.ShopDestination> {
                ShopScreen()
            }
            composable<BottomNavBarDestination.AddEventDestination> {
                AddEventScreen()
            }
            composable<BottomNavBarDestination.ProfileDestination> {
                ProfileScreen()
            }
            innerPadding
        }
    }
}
