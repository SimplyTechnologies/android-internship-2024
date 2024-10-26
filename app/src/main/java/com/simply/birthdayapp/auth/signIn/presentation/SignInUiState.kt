package com.simply.birthdayapp.auth.signIn.presentation

sealed class SignInUiState {
    data object Loading : SignInUiState()
    data class Success(val message: String) : SignInUiState()
    data class Error(val message: String) : SignInUiState()
}