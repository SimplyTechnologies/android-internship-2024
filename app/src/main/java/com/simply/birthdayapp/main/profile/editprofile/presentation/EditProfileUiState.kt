package com.simply.birthdayapp.main.profile.editprofile.presentation

sealed class EditProfileUiState {
    data object Loading : EditProfileUiState()
    data object Success : EditProfileUiState()
    data class Error(val message: String) : EditProfileUiState()
}