package com.simply.birthdayapp.main.home.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.simply.birthdayapp.commondomain.model.Birthday
import com.simply.birthdayapp.main.home.domain.usecase.GetBirthdaysUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeViewModel(
    private val birthdayUseCase: GetBirthdaysUseCase
) : ViewModel() {
    init {
        fetchBirthdays()
    }

    private val _birthdays = MutableStateFlow<List<Birthday>>(emptyList())
    val birthdays = _birthdays.asStateFlow()

    private fun fetchBirthdays() {
        viewModelScope.launch {
            birthdayUseCase.invoke().collect {
                _birthdays.value = it
            }
        }
    }

}