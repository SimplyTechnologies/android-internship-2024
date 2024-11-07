package com.simply.birthdayapp.main.home.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.simply.birthdayapp.commondomain.model.Birthday
import com.simply.birthdayapp.core.result.Result
import com.simply.birthdayapp.main.home.domain.usecase.GetBirthdaysUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class HomeViewModel(
    private val birthdayUseCase: GetBirthdaysUseCase
) : ViewModel() {
    init {
        fetchBirthdays()
    }

    private val _birthdaysUiState = MutableStateFlow<Result<List<Birthday>>>(Result.Loading(emptyList()))
    val birthdaysUiState = _birthdaysUiState.asStateFlow()

    private fun fetchBirthdays() {
        viewModelScope.launch {
            birthdayUseCase.invoke().onEach {
                when (val uiState = it) {
                    is Result.Loading -> _birthdaysUiState.value = Result.Loading(emptyList())
                    is Result.Error -> _birthdaysUiState.value = Result.Error(uiState.message, emptyList())
                    is Result.Success -> _birthdaysUiState.value = Result.Success(uiState.data)
                }
            }.launchIn(viewModelScope)
        }
    }
}