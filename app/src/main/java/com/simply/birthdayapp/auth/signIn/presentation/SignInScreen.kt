package com.simply.birthdayapp.auth.signIn.presentation

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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
        navigateToLanding = navigateToLanding,
        saveLoggedInState = viewModel::setSignedIn
    )
}

@Composable
fun SignInComposable(
    modifier: Modifier = Modifier,
    navigateToMain: () -> Unit,
    navigateToLanding: () -> Unit,
    saveLoggedInState: (Boolean) -> Unit = {},
) {

    val emailText = remember { mutableStateOf("") }
    val emailError = remember { mutableStateOf("") }

    val passwordText = remember { mutableStateOf("") }
    val passwordError = remember { mutableStateOf("") }


    BackHandler {
        navigateToLanding()
    }

    Box(modifier = modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .align(Alignment.Center)
                .padding(38.dp)
                .clip(
                    RoundedCornerShape(24.dp)
                )
                .background(Color.White)
                .padding(23.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                modifier = Modifier.padding(top = 15.dp),
                text = "Sign In",
                style = AuthTitleTextStyle
            )

            InputTextField(
                modifier = Modifier.padding(top = 15.dp, bottom = 12.dp),
                placeholder = "Email",
                value = emailText,
                error = emailError
            )
            InputTextField(
                placeholder = "Password",
                value = passwordText,
                error = passwordError,
                isPassword = true
            )

            AuthedButton(modifier = Modifier.padding(top = 51.dp), text = "Sign In") {
                navigateToMain()
                saveLoggedInState(true)
            }
        }
    }
}

@Preview
@Composable
private fun Preview() {
    SignInComposable(navigateToMain = {}, navigateToLanding = {})
}