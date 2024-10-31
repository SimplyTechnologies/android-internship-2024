package com.simply.birthdayapp.auth.authnavigation.presentation.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.simply.birthdayapp.auth.authnavigation.domain.usecase.IsSignedInUseCase
import com.simply.birthdayapp.auth.navigation.Destination
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class AuthViewModel(private val isSignedInUseCase: IsSignedInUseCase, ) : ViewModel() {

    private val _startDestination = MutableSharedFlow<Destination?>(
        extraBufferCapacity = 1,
        onBufferOverflow = BufferOverflow.DROP_OLDEST,
        replay = 1,
    )
    val startDestination: SharedFlow<Destination?> = _startDestination.asSharedFlow()

    init {
        viewModelScope.launch {
            isSignedInUseCase.invoke().collect { isSignedIn ->
                _startDestination.emit(isSignedIn)
            }
        }
    }
}