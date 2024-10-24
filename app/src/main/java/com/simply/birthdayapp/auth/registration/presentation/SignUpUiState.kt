package com.simply.birthdayapp.auth.registration.presentation

sealed class SignUpUiState {
    data object Idle : SignUpUiState()
    data object Loading: SignUpUiState()
    data class Success(val message: String) : SignUpUiState()
    data class UserExists(val message: String) : SignUpUiState()
    data class GeneralError(val message: String) : SignUpUiState()
}