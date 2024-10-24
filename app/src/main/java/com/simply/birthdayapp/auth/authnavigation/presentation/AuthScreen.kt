package com.simply.birthdayapp.auth.authnavigation.presentation

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
import com.simply.birthdayapp.auth.navigation.AuthNavigation
import com.simply.birthdayapp.auth.navigation.Destination
import com.simply.birthdayapp.commonpresentation.components.actionbar.auth.AuthActionBar
import org.koin.androidx.compose.koinViewModel

@Composable
fun AuthScreen(
    modifier: Modifier = Modifier,
    viewModel: AuthViewModel = koinViewModel(),
    navigateToMain: () -> Unit,
) {
    val navController = rememberNavController()
    val startDest by viewModel.startDestination.collectAsState(initial = null)
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    val isNotLandingScreen = currentDestination?.hierarchy?.any { destination ->
        destination.route == Destination.LandingDestination::class.qualifiedName
    } == false

    startDest?.let {
        Scaffold(modifier = Modifier.fillMaxSize(), topBar = {
            AuthActionBar(
                modifier = Modifier.padding(WindowInsets.statusBars.asPaddingValues()),
                showTopBar = isNotLandingScreen,
            ) {
                navController.navigate(Destination.LandingDestination) {
                    popUpTo(navController.graph.findStartDestination().id) {
                        saveState = true
                    }
                    restoreState = true
                    launchSingleTop = true
                }
            }
        }) { innerPadding ->
            AuthNavigation(modifier.padding(innerPadding), navController, navigateToMain, it)
        }
    }
}