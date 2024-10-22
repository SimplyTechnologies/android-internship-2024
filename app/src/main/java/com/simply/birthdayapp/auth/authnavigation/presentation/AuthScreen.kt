package com.simply.birthdayapp.auth.authnavigation.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.simply.birthdayapp.auth.navigation.AuthNavigation
import org.koin.androidx.compose.koinViewModel

@Composable
fun AuthScreen(
    modifier: Modifier = Modifier,
    viewModel: AuthViewModel = koinViewModel(),
    navigateToMain: () -> Unit,
) {
    val navController = rememberNavController()
    val startDest by viewModel.startDestination.collectAsState(initial = null)

    startDest?.let {
        AuthNavigation(modifier, navController, navigateToMain, it)
    }
}