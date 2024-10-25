package com.simply.birthdayapp.auth.signIn.presentation

sealed class SignInUiState {
    data object Idle : SignInUiState()
    data object Loading : SignInUiState()
    data object Success : SignInUiState()
    data class Error(val message: String) : SignInUiState()
}