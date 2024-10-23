package com.simply.birthdayapp.auth.authnavigation.presentation.home

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.simply.birthdayapp.auth.signIn.presentation.SignInComposable
import com.simply.birthdayapp.auth.signIn.presentation.SignInViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: SignInViewModel = koinViewModel(),
) {
    Text("HomeScreen")
}