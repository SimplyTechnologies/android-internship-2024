package com.simply.birthdayapp.main.profile.profile.presentation

import com.simply.birthdayapp.main.profile.profile.domain.model.UserDomain

sealed class ProfileUiState {
    data object Loading : ProfileUiState()
    data class Success(val data: UserDomain) : ProfileUiState()
    data class Error(val message: String) : ProfileUiState()
}