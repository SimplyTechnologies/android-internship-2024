package com.simply.birthdayapp.main.profile.editprofile.presentation

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.simply.birthdayapp.commonpresentation.theme.DarkPink
import com.simply.birthdayapp.main.profile.profile.domain.model.UserDomain
import org.koin.androidx.compose.getViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun EditMyProfileScreen(
    userDomain: UserDomain,
    modifier: Modifier = Modifier,
    viewModel: EditMyProfileViewModel = getViewModel { parametersOf(userDomain) },
    navigateBack: () -> Unit = {}
) {
    val context = LocalContext.current
    val screenUiState by viewModel.screenUiState.collectAsState()

    when (val state = screenUiState) {
        is EditProfileUiState.Error -> {
            val errorMessage = state.message
            Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show()
        }

        EditProfileUiState.Loading -> {
            Box(modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator(strokeWidth = 4.dp, color = DarkPink)
            }
        }

        EditProfileUiState.Success -> {
            navigateBack()
        }

        null -> {
            EditMyProfileScreenContent(modifier = modifier.fillMaxSize(), viewModel = viewModel)
        }
    }
}

