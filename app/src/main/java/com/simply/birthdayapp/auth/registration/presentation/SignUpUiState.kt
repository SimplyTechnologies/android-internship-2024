package com.simply.birthdayapp.auth.registration.presentation

sealed class SignUpUiState {
    data object Loading: SignUpUiState()
    data class Success(val message: String) : SignUpUiState()
    data class Error(val message: String) : SignUpUiState()
}