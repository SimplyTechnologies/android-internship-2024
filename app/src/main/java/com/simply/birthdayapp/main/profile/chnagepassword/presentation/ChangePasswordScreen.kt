package com.simply.birthdayapp.main.profile.chnagepassword.presentation

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import org.koin.androidx.compose.koinViewModel

@Composable
fun ChangePasswordScreen(
    modifier: Modifier = Modifier,
    viewModel: ChangePasswordViewModel = koinViewModel(),
    navigateToLoginScreen: () -> Unit = {}
) {
    val uiState by viewModel.uiState.collectAsState()
    val context = LocalContext.current

    val shouldNavigateToLoginScreen by viewModel.isNavigationToLoginScreen.collectAsState(false)

    LaunchedEffect(shouldNavigateToLoginScreen) {
        if (shouldNavigateToLoginScreen) {
            navigateToLoginScreen()
        }
    }

    when (uiState) {
        is ChangePasswordUiState.Error -> {
            Toast.makeText(
                context,
                stringResource((uiState as ChangePasswordUiState.Error).message.toInt()),
                Toast.LENGTH_SHORT
            ).show()
            viewModel.setUiState(null)
        }

        ChangePasswordUiState.Success -> {
            viewModel.doSignOut()
        }

        else -> {
            ChangePasswordScreenContent(
                modifier = modifier,
                viewModel = viewModel,
                isLoading = uiState == ChangePasswordUiState.Loading
            )
        }
    }
}


@Preview
@Composable
private fun ChangePasswordScreenPreview() {
    ChangePasswordScreen()
}
