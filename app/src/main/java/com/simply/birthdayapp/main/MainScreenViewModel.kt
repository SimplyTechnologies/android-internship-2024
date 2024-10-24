package com.simply.birthdayapp.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.simply.birthdayapp.auth.navigation.Destination
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class MainScreenViewModel : ViewModel() {

    private val _startDestination = MutableSharedFlow<Destination?>()
    val startDestination: SharedFlow<Destination?> = _startDestination.asSharedFlow()

    init {
        viewModelScope.launch {

        }
    }
}