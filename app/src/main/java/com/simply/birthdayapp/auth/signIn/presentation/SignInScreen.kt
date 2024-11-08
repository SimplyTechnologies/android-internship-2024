package com.simply.birthdayapp.auth.signIn.presentation

import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.simply.birthdayapp.R
import com.simply.birthdayapp.commonpresentation.components.button.AuthedButton
import com.simply.birthdayapp.commonpresentation.components.lottie.Animation
import com.simply.birthdayapp.commonpresentation.components.textfield.InputTextField
import com.simply.birthdayapp.commonpresentation.theme.AuthTitleTextStyle
import com.simply.birthdayapp.commonpresentation.theme.DarkPink
import org.koin.androidx.compose.koinViewModel

@Composable
fun SignInScreen(
    modifier: Modifier = Modifier,
    viewModel: SignInViewModel = koinViewModel(),
    navigateToMain: () -> Unit,
    navigateToLanding: () -> Unit,
) {
    SignInComposable(
        modifier = modifier.fillMaxSize(),
        navigateToMain = navigateToMain,
        viewModel = viewModel,
        navigateToLanding = navigateToLanding,
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
    val passwordError = viewModel.passwordError.collectAsState()

    val uiState by viewModel.uiState.collectAsState()

    val context = LocalContext.current

    LaunchedEffect(uiState) {
        if (uiState is SignInUiState.Success) {
            navigateToMain()
        }
    }

    Box(
        modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center
    ) {

        Box(contentAlignment = Alignment.BottomCenter) {
            Animation(
                modifier = Modifier
                    .fillMaxWidth()
                    .offset(y = (-230).dp),
                res = R.raw.birthday_cong_anim
            )
            Column(
                modifier = Modifier
                    .verticalScroll(rememberScrollState())
                    .imePadding()
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
                    viewModel.signIn()
                }
            }

        }

        when (val state = uiState) {
            is SignInUiState.Error -> {
                val errorMessage =
                    if (state.message == stringResource(R.string.unauthorized)) stringResource(R.string.error_unauthorized_user)
                    else state.message


                Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show()
                viewModel.resetState()

            }

            SignInUiState.Loading -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.Black.copy(alpha = 0.3f)),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(
                        strokeWidth = 3.dp, color = DarkPink
                    )
                }

            }

            is SignInUiState.Success -> {
                saveLoggedInState(true)
            }

            null -> {}
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