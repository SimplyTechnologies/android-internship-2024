package com.simply.birthdayapp.auth.authnavigation.presentation.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.simply.birthdayapp.auth.authnavigation.domain.usecase.GetAuthInitialDestinationUseCase
import com.simply.birthdayapp.auth.navigation.Destination
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class AuthViewModel(
    private val getAuthInitialDestinationUseCase: GetAuthInitialDestinationUseCase
) : ViewModel() {

    private val _startDestination = MutableSharedFlow<Destination?>(replay = 1)
    val startDestination: SharedFlow<Destination?> = _startDestination.asSharedFlow()

    init {
        viewModelScope.launch {
            getAuthInitialDestinationUseCase.invoke().collect { isSignedIn ->
                _startDestination.emit(isSignedIn)
            }
        }
    }
}