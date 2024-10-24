package com.simply.birthdayapp.auth.registration.presentation

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.simply.birthdayapp.R
import com.simply.birthdayapp.auth.registration.domain.model.SignUpInput
import com.simply.birthdayapp.commonpresentation.components.button.AuthedButton
import com.simply.birthdayapp.commonpresentation.components.textfield.InputTextField
import com.simply.birthdayapp.commonpresentation.theme.AuthTitleTextStyle
import org.koin.androidx.compose.koinViewModel

@Composable
fun RegistrationScreen(
    viewModel: RegistrationViewModel = koinViewModel(),
    navigateToLanding: () -> Unit
                       ) {
    val verticalScrollState = rememberScrollState()
    val name by viewModel.name.collectAsState()
    val surname by viewModel.surname.collectAsState()
    val email by viewModel.email.collectAsState()
    val password by viewModel.password.collectAsState()
    val repeatedPassword by viewModel.repeatedPassword.collectAsState()

    val nameError by viewModel.nameError.collectAsState()
    val surnameError by viewModel.surnameError.collectAsState()
    val emailError by viewModel.emailError.collectAsState()
    val passwordError by viewModel.passwordError.collectAsState()
    val repeatedPasswordError by viewModel.repeatedPasswordError.collectAsState()

    val uiState by viewModel.uiState.collectAsState()

    when (uiState) {
        is SignUpUiState.Success -> {

            Toast.makeText(
                LocalContext.current,
                (uiState as SignUpUiState.Success).message,
                Toast.LENGTH_SHORT
            ).show()
            viewModel.resetState()
            navigateToLanding.invoke()
        }

        is SignUpUiState.UserExists -> {
            Toast.makeText(
                LocalContext.current,
                (uiState as SignUpUiState.UserExists).message,
                Toast.LENGTH_SHORT
            ).show()
            viewModel.resetState()
            navigateToLanding.invoke()

        }

        is SignUpUiState.GeneralError -> {
            val errorMessage = (uiState as SignUpUiState.GeneralError).message
            AlertDialog(
                onDismissRequest = {
                    viewModel.resetState()
                },
                title = { Text(text = "Registration Error") },
                text = { Text(text = errorMessage) },
                confirmButton = {
                    TextButton(onClick = {
                        viewModel.resetState()
                    }) {
                        Text(text = "Ok")
                    }
                }
            )
        }

        is SignUpUiState.Loading -> {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CircularProgressIndicator(
                    modifier = Modifier.size(48.dp)
                )
            }

        }

        SignUpUiState.Idle -> {

        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(verticalScrollState),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(56.dp))

        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp), shape = RoundedCornerShape(32.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(30.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Text(
                    text = stringResource(R.string.register), style = AuthTitleTextStyle
                )

                // Name Field
                InputTextField(
                    modifier = Modifier.fillMaxWidth(),
                    textValue = name,
                    error = nameError,
                    placeholder = stringResource(R.string.name),
                    onValueChange = {
                        viewModel.setName(it)
                        viewModel.validateName()
                    },
                )

                // Surname Field
                InputTextField(modifier = Modifier.fillMaxWidth(),
                    textValue = surname,
                    error = surnameError,
                    placeholder = stringResource(R.string.surname),
                    onValueChange = {
                        viewModel.setSurname(it)
                        viewModel.validateSurname()
                    })

                // Email Field
                InputTextField(modifier = Modifier.fillMaxWidth(),
                    textValue = email,
                    error = emailError,
                    placeholder = stringResource(R.string.email),
                    onValueChange = {
                        viewModel.setEmail(it)
                        viewModel.validateEmail()
                    })

                // Password Field
                InputTextField(modifier = Modifier.fillMaxWidth(),
                    textValue = password,
                    error = passwordError,
                    isPassword = true,
                    placeholder = stringResource(R.string.password),
                    onValueChange = {
                        viewModel.setPassword(it)
                        viewModel.validatePassword()
                    })

                // Repeated Password Field
                InputTextField(modifier = Modifier.fillMaxWidth(),
                    textValue = repeatedPassword,
                    error = repeatedPasswordError,
                    isPassword = true,
                    placeholder = stringResource(R.string.repeat_password),
                    keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
                    onValueChange = {
                        viewModel.setRepeatedPassword(it)
                        viewModel.validateRepeatedPassword()
                    })

                // Register Button
                AuthedButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp),
                    text = stringResource(R.string.register),
                    isEnabled = viewModel.isRegisterEnabled.value
                ) {
                    viewModel.signUp(
                        SignUpInput(
                            email = email,
                            firstName = name,
                            lastName = surname,
                            password = password
                        )
                    )
                }
            }
        }
    }
}


