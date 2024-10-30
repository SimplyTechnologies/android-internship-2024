package com.simply.birthdayapp.main.profile.chnagepassword.presentation

sealed class ChangePasswordUiState {
    data object Success : ChangePasswordUiState()
    data class Error(val message: String) : ChangePasswordUiState()
    data object Loading : ChangePasswordUiState()

}
