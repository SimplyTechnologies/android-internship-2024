package com.simply.birthdayapp.main.profile.profile.presentation

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.simply.birthdayapp.R
import com.simply.birthdayapp.commonpresentation.components.actionbar.auth.TopAppBarWithBackButton
import com.simply.birthdayapp.commonpresentation.components.button.AccountOptionButton
import com.simply.birthdayapp.commonpresentation.components.image.NetworkImage
import com.simply.birthdayapp.commonpresentation.theme.DarkPink
import com.simply.birthdayapp.commonpresentation.theme.PrimaryTextStyle
import com.simply.birthdayapp.main.profile.profile.domain.model.UserDomain
import org.koin.androidx.compose.koinViewModel

@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier,
    viewModel: ProfileViewModel = koinViewModel(),
    navigateToEditAccount: (UserDomain) -> Unit = {},
    navigateToLogin: () -> Unit = {},
    navigateToChangePassword: () -> Unit = {}
) {
    LaunchedEffect(Unit) {
        viewModel.fetchUserProfile()
    }

    val state by viewModel.profileUiState.collectAsState()
    val context = LocalContext.current

    LaunchedEffect(state) {
        if (state is ProfileUiState.Error) {
            val errorMessage = (state as ProfileUiState.Error).message
            Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show()
        }
    }

    when (val uiState = state) {
        ProfileUiState.Loading -> {
            Box(modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator(strokeWidth = 4.dp, color = DarkPink)
            }
        }

        is ProfileUiState.Success -> {
            ProfileContent(
                modifier = modifier,
                data = uiState.data,
                navigateToEditAccount = navigateToEditAccount,
                navigateToChangePassword = navigateToChangePassword
            ) {
                viewModel.logOut {
                    navigateToLogin()
                }
            }
        }

        else -> {}
    }
}

@Composable
private fun ProfileContent(
    modifier: Modifier = Modifier,
    data: UserDomain = UserDomain.default,
    navigateToEditAccount: (UserDomain) -> Unit = {},
    navigateToChangePassword: () -> Unit = {},
    signOutClick: () -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        TopAppBarWithBackButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 48.dp),
            showTopBar = true,
            showBackButton = false
        )

        NetworkImage(modifier = Modifier.size(100.dp), url = data.image)

        Text(
            modifier = Modifier.padding(top = 32.dp, bottom = 16.dp),
            text = "${data.firstName} ${data.lastName}",
            style = PrimaryTextStyle,
            fontSize = 20.sp
        )
        Text(
            modifier = Modifier.padding(bottom = 52.dp),
            text = data.email,
            style = PrimaryTextStyle,
            fontSize = 20.sp
        )

        AccountOptionButton(
            modifier = Modifier.fillMaxWidth(), text = stringResource(R.string.change_password)
        ) {
            navigateToChangePassword()
        }

        AccountOptionButton(
            modifier = Modifier.fillMaxWidth(), text = stringResource(R.string.edit_account)
        ) {
            navigateToEditAccount(data)
        }

        AccountOptionButton(
            modifier = Modifier.fillMaxWidth(), text = stringResource(R.string.sign_out)
        ) {
            signOutClick()
        }
    }
}
