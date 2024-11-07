package com.simply.birthdayapp.main.home.presentation

import com.simply.birthdayapp.commondomain.model.Birthday

sealed class HomeUiState {
    data object Loading : HomeUiState()
    data class Success(val birthday: Birthday) : HomeUiState()
    data class Error(val message: String) : HomeUiState()
}