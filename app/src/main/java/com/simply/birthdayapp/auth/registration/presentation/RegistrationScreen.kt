package com.simply.birthdayapp.auth.registration.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.simply.birthdayapp.R
import com.simply.birthdayapp.commonpresentation.components.button.AuthedButton
import com.simply.birthdayapp.commonpresentation.components.textfield.InputTextField
import com.simply.birthdayapp.commonpresentation.theme.AuthTitleTextStyle
import org.koin.androidx.compose.koinViewModel

@Composable
fun RegistrationScreen(modifier: Modifier = Modifier) {
    val verticalScrollState = rememberScrollState()


    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(verticalScrollState),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(58.dp))

        RegistrationScreenContent()
    }
}

@Composable
private fun RegistrationScreenContent(
    viewModel: RegistrationViewModel = koinViewModel()
) {
    val name = viewModel.name
    val surname = viewModel.surname
    val email = viewModel.email
    val password = viewModel.password
    val repeatedPassword = viewModel.repeatedPassword

    val nameError = viewModel.nameError
    val surnameError = viewModel.surnameError
    val emailError = viewModel.emailError
    val passwordError = viewModel.passwordError
    val repeatedPasswordError = viewModel.repeatedPasswordError

    val isRegisterEnabled by viewModel.isRegisterEnabled

    DisposableEffect(Unit) {
        onDispose {
            viewModel.clearFields()
        }
    }


    // Registration form content
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(24.dp),
        shape = RoundedCornerShape(30.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(30.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = stringResource(R.string.register),
                style = AuthTitleTextStyle
            )

            // Name Field
            InputTextField(
                modifier = Modifier.fillMaxWidth(),
                textValue = name,
                error = nameError,
                placeholder = stringResource(R.string.name),
                onValueChange = {
                    viewModel.name.value = it
                },
                onFocusChange = { focused ->
                    if (!focused) viewModel.validateName()
                    viewModel.validateRegisterButtonState()
                }
            )

            // Surname Field
            InputTextField(
                modifier = Modifier.fillMaxWidth(),
                textValue = surname,
                error = surnameError,
                placeholder = stringResource(R.string.surname),
                onValueChange = {
                    viewModel.surname.value = it
                },
                onFocusChange = { focused ->
                    if (!focused) viewModel.validateSurname()
                    viewModel.validateRegisterButtonState()
                }
            )

            // Email Field
            InputTextField(
                modifier = Modifier.fillMaxWidth(),
                textValue = email,
                error = emailError,
                placeholder = stringResource(R.string.email),
                onValueChange = {
                    viewModel.email.value = it
                },
                onFocusChange = { focused ->
                    if (!focused) viewModel.validateEmail()
                    viewModel.validateRegisterButtonState()
                }
            )

            // Password Field
            InputTextField(
                modifier = Modifier.fillMaxWidth(),
                textValue = password,
                error = passwordError,
                isPassword = true,
                placeholder = stringResource(R.string.password),
                onValueChange = {
                    viewModel.password.value = it
                },
                onFocusChange = { focused ->
                    if (!focused) viewModel.validatePassword()
                    viewModel.validateRegisterButtonState()
                }
            )

            // Repeated Password Field
            InputTextField(
                modifier = Modifier.fillMaxWidth(),
                textValue = repeatedPassword,
                error = repeatedPasswordError,
                isPassword = true,
                placeholder = stringResource(R.string.repeat_password),
                keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
                onValueChange = {
                    viewModel.repeatedPassword.value = it
                },
                onFocusChange = { focused ->
                    if (!focused) viewModel.validateRepeatedPassword()
                    viewModel.validateRegisterButtonState()
                }
            )

            // Register Button
            AuthedButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 25.dp),
                text = stringResource(R.string.register),
                isEnabled = isRegisterEnabled
            ) {
                // Handle register action
            }
        }
    }
}
