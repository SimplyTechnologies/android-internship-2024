package com.simply.birthdayapp.main.profile.profile.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.simply.birthdayapp.auth.signIn.domain.usecase.SetAuthInitialScreenStateUseCase
import com.simply.birthdayapp.core.ErrorMessages
import com.simply.birthdayapp.core.result.Result
import com.simply.birthdayapp.main.profile.chnagepassword.domain.usecase.ClearAccessTokenUseCase
import com.simply.birthdayapp.main.profile.profile.domain.usecase.GetUserProfileUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class ProfileViewModel(
    private val getUserProfileUseCase: GetUserProfileUseCase,
    private val clearAccessTokenUseCase: ClearAccessTokenUseCase,
    private val setAuthInitialScreenStateUseCase: SetAuthInitialScreenStateUseCase
) : ViewModel() {
    private val _profileUiState = MutableStateFlow<ProfileUiState>(ProfileUiState.Loading)
    val profileUiState: StateFlow<ProfileUiState> = _profileUiState.asStateFlow()

    fun fetchUserProfile() {
        getUserProfileUseCase.invoke().onEach {
            when (it) {
                is Result.Error -> _profileUiState.value = ProfileUiState.Error(it.message)
                is Result.Success -> _profileUiState.value = ProfileUiState.Success(it.data)
                is Result.Loading -> _profileUiState.value = ProfileUiState.Loading
            }
        }.catch {
            _profileUiState.value = ProfileUiState.Error(
                it.message ?: ErrorMessages.GENERAL_ERROR
            )
        }.launchIn(viewModelScope)
    }

    fun logOut(onTokenCleared: () -> Unit) {
        viewModelScope.launch {
            setAuthInitialScreenStateUseCase.invoke(false)
            clearAccessTokenUseCase.invoke()
            onTokenCleared()
        }
    }
}