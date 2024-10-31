package com.simply.birthdayapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.simply.birthdayapp.commondomain.usecase.IsUserLoggedUseCase
import com.simply.birthdayapp.commonpresentation.navigation.AppDestination
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class MainViewModel(private val isUserLoggedUseCase: IsUserLoggedUseCase) : ViewModel() {
    private val _startDestination = MutableSharedFlow<AppDestination?>(replay = 1)
    val startDestination: SharedFlow<AppDestination?> = _startDestination.asSharedFlow()

    init {
        viewModelScope.launch {
            isUserLoggedUseCase().collect { isLoggedIn ->
                _startDestination.emit(
                    if (isLoggedIn) AppDestination.MainDestination else AppDestination.AuthDestination
                )
            }
        }
    }
}
