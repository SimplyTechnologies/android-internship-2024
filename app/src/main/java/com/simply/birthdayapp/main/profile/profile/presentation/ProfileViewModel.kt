package com.simply.birthdayapp.main.profile.profile.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
    private val clearAccessTokenUseCase: ClearAccessTokenUseCase
) : ViewModel() {
    private val _profileUiState = MutableStateFlow<ProfileUiState>(ProfileUiState.Loading)
    val profileUiState: StateFlow<ProfileUiState> = _profileUiState.asStateFlow()

    fun fetchUserProfile() {
        getUserProfileUseCase.invoke().onEach {
            when (it) {
                is Result.Error -> _profileUiState.emit(ProfileUiState.Error(it.message))
                is Result.Success -> _profileUiState.emit(ProfileUiState.Success(it.data))
                is Result.Loading -> _profileUiState.emit(ProfileUiState.Loading)
            }
        }.catch {
            _profileUiState.emit(
                ProfileUiState.Error(
                    it.message ?: ErrorMessages.GENERAL_ERROR
                )
            )
        }.launchIn(viewModelScope)
    }

    fun logOut() {
        viewModelScope.launch {
            clearAccessTokenUseCase.invoke()
        }
    }
}