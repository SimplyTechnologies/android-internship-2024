package com.simply.birthdayapp.main

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.simply.birthdayapp.commonpresentation.navigation.BottomNavItem
import com.simply.birthdayapp.commonpresentation.navigation.BottomNavigationBar
import com.simply.birthdayapp.main.home.presentation.HomeScreen
import com.simply.birthdayapp.main.shop.presentation.ShopScreen
import com.simply.birthdayapp.main.addEvent.presentation.AddEventScreen
import com.simply.birthdayapp.main.navigation.BottomNavBarDestination
import com.simply.birthdayapp.main.navigation.MainNavigation
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
        MainNavigation(
            modifier = modifier.padding(innerPadding),
            navController = navController,
            startDestination = BottomNavBarDestination.HomeDestination
        )
    }

//    val navController = rememberNavController()
//    val startDest by viewModel.startDestination.collectAsState(initial = null)
//    val navBackStackEntry by navController.currentBackStackEntryAsState()
//    val currentDestination = navBackStackEntry?.destination
//    val isNotLandingScreen = currentDestination?.hierarchy?.any { destination ->
//        destination.route == Destination.LandingDestination::class.qualifiedName
//    } == false
//
//    Scaffold(modifier = Modifier.fillMaxSize(), bottomBar = { BottomNavigationBar(navController) }) { innerPadding ->
//        MainNavigation(modifier.padding(innerPadding), navController, BottomNavBarDestination.HomeDestination)
//    }
}
