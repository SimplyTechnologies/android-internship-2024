package com.simply.birthdayapp.main.addEvent.presentation

sealed class AddEventUiState {
    data object Loading : AddEventUiState()
    data class Success(val message: String) : AddEventUiState()
    data class Error(val message: String) : AddEventUiState()
}