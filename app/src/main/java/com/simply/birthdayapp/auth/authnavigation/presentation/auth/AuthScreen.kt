package com.simply.birthdayapp.auth.authnavigation.presentation.auth

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.simply.birthdayapp.auth.navigation.AuthNavigation
import com.simply.birthdayapp.auth.navigation.Destination
import com.simply.birthdayapp.commonpresentation.components.actionbar.auth.TopAppBarWithBackButton
import com.simply.birthdayapp.commonpresentation.theme.AppBackgroundColor
import org.koin.androidx.compose.koinViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
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
        Scaffold(modifier = modifier
            .fillMaxSize()
            .background(AppBackgroundColor), topBar = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(AppBackgroundColor)
            ) {
                TopAppBarWithBackButton(
                    modifier = Modifier
                        .padding(horizontal = 24.dp),
                    showTopBar = isNotLandingScreen,
                    showBackButton = true
                ) {
                    navController.navigate(Destination.LandingDestination)
                }

            }
        }) { innerPadding ->
            AuthNavigation(
                modifier = Modifier,
                navController = navController,
                navigateToMain = navigateToMain,
                startDestination = it,
            )
        }
    }
}