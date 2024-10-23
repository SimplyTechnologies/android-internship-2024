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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
    navigateToMain: () -> Unit,
    navigateToLanding: () -> Unit,
    saveLoggedInState: (Boolean) -> Unit = {},
) {

    val emailText = viewModel.emailText
    val passwordText = viewModel.passwordText
    val emailError = viewModel.emailError
    val passwordError = viewModel.passwordError

    var isSigneInButtonEnabled by remember { mutableStateOf(false) }
    LaunchedEffect(emailText.value, passwordText.value) {
        isSigneInButtonEnabled = emailText.value.isNotEmpty() && passwordText.value.isNotEmpty()
        println("state:: $isSigneInButtonEnabled")
    }

    BackHandler {
        navigateToLanding()
    }

    Box(modifier = modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .align(Alignment.Center)
                .padding(38.dp)
                .clip(RoundedCornerShape(24.dp))
                .background(Color.White)
                .padding(23.dp),
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
                textValue = emailText,
                error = emailError,
                onValueChange = {
                    emailText.value = it
                })

            InputTextField(placeholder = stringResource(R.string.password),
                textValue = passwordText,
                error = passwordError,
                isPassword = true,
                keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
                onValueChange = {
                    passwordText.value = it
                })

            AuthedButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 25.dp, end = 25.dp, top = 51.dp),
                isEnabled = isSigneInButtonEnabled,
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
    SignInComposable(modifier = Modifier,
        viewModel = koinViewModel(),
        navigateToMain = {},
        navigateToLanding = {})
}