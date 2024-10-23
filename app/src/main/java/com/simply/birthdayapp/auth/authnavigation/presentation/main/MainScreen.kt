package com.simply.birthdayapp.auth.authnavigation.presentation.main

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.simply.birthdayapp.auth.authnavigation.presentation.auth.AuthViewModel
import com.simply.birthdayapp.auth.navigation.AuthNavigation
import com.simply.birthdayapp.auth.navigation.Destination
import com.simply.birthdayapp.auth.navigation.MainNavigation
import com.simply.birthdayapp.commonpresentation.components.actionbar.auth.AuthActionBar
import com.simply.birthdayapp.commonpresentation.navigation.BottomNavigationBar
import org.koin.androidx.compose.koinViewModel

@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    viewModel: MainScreenViewModel = koinViewModel(),
) {
    val navController = rememberNavController()
    val startDest by viewModel.startDestination.collectAsState(initial = null)
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    val isNotLandingScreen = currentDestination?.hierarchy?.any { destination ->
        destination.route == Destination.LandingDestination::class.qualifiedName
    } == false

        Scaffold(modifier = Modifier.fillMaxSize(), bottomBar = { BottomNavigationBar(navController) }) { innerPadding ->
            MainNavigation(modifier.padding(innerPadding), navController, Destination.HomeDestination)
    }
}