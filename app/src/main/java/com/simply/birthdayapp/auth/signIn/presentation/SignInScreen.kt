package com.simply.birthdayapp.auth.signIn.presentation

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.simply.birthdayapp.R
import com.simply.birthdayapp.commonpresentation.components.button.AuthedButton
import com.simply.birthdayapp.commonpresentation.components.textfield.InputTextField
import com.simply.birthdayapp.commonpresentation.theme.AuthTitleTextStyle
import org.koin.androidx.compose.koinViewModel

@Composable
fun SignInScreen(
    modifier: Modifier = Modifier,
    viewModel: SignInViewModel = koinViewModel(),
    navigateToMain: () -> Unit,
    navigateToLanding: () -> Unit,
) {
    SignInComposable(
        modifier = modifier,
        navigateToMain = navigateToMain,
        viewModel = viewModel,
        navigateToLanding = navigateToLanding,
        saveLoggedInState = viewModel::setSignedIn
    )
}

@Composable
fun SignInComposable(
    modifier: Modifier = Modifier,
    viewModel: SignInViewModel,
    navigateToMain: () -> Unit = {},
    navigateToLanding: () -> Unit = {},
    saveLoggedInState: (Boolean) -> Unit = {},
) {
    BackHandler {
        navigateToLanding()
    }

    val email = viewModel.emailText.collectAsState()
    val passwordText = viewModel.passwordText.collectAsState()

    val emailError = viewModel.emailError.collectAsState()
    val passwordError = viewModel.emailError.collectAsState()

    Box(modifier = modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .align(Alignment.Center)
                .padding(38.dp)
                .clip(RoundedCornerShape(24.dp))
                .background(Color.White)
                .padding(24.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                modifier = Modifier.padding(top = 15.dp),
                text = stringResource(R.string.sign_in),
                style = AuthTitleTextStyle
            )

            InputTextField(modifier = Modifier.padding(top = 15.dp, bottom = 12.dp),
                placeholder = stringResource(R.string.email),
                textValue = email.value,
                error = emailError.value,
                onValueChange = {
                    viewModel.setEmailText(it)
                })

            InputTextField(placeholder = stringResource(R.string.password),
                textValue = passwordText.value,
                error = passwordError.value,
                isPassword = true,
                keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
                onValueChange = {
                    viewModel.setPasswordText(it)
                })

            AuthedButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 26.dp, end = 26.dp, top = 48.dp),
                isEnabled = viewModel.isSignInButtonEnable.value,
                text = stringResource(R.string.sign_in)
            ) {
                navigateToMain()
                saveLoggedInState(true)
            }
        }
    }
}

@Preview
@Composable
private fun Preview() {
    SignInComposable(
        viewModel = koinViewModel(),
    )
}