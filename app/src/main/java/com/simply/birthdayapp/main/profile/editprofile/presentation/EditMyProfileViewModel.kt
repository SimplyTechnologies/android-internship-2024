package com.simply.birthdayapp.main.profile.editprofile.presentation

import android.content.Context
import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.simply.birthdayapp.commondomain.usecase.ImageEncodeUseCase
import com.simply.birthdayapp.main.profile.editprofile.domain.model.UpdateProfileInput
import com.simply.birthdayapp.main.profile.editprofile.domain.usecase.EditUserProfileUseCase
import com.simply.birthdayapp.main.profile.profile.domain.model.UserDomain
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class EditMyProfileViewModel(
    user: UserDomain,
    private val editUserProfileUseCase: EditUserProfileUseCase,
    private val imageEncodeUseCase: ImageEncodeUseCase
) : ViewModel() {

    private val _screenUiState = MutableStateFlow<EditProfileUiState?>(null)
    val screenUiState = _screenUiState.asStateFlow()

    private val _currentUser = MutableStateFlow(user)
    private val currentUser = _currentUser.asStateFlow()

    private val _name = MutableStateFlow(currentUser.value.firstName)
    val name = _name.asStateFlow()

    private val _surname = MutableStateFlow(currentUser.value.lastName)
    val surname = _surname.asStateFlow()

    private val _nameError = MutableStateFlow<Int?>(null)
    val nameError = _nameError.asStateFlow()

    private val _surnameError = MutableStateFlow<Int?>(null)
    val surnameError = _surnameError.asStateFlow()

    private val _imageUrl = MutableStateFlow(currentUser.value.image)
    val imageUrl = _imageUrl.asStateFlow()

    private val _imageUri = MutableStateFlow<Uri?>(null)
    val imageUri = _imageUri.asStateFlow()

    private val _doneButtonEnableState = MutableStateFlow(false)
    val doneButtonEnableState = _doneButtonEnableState.asStateFlow()

    private val _updatedUser = MutableStateFlow(
        user.copy(
            firstName = _name.value,
            lastName = _surname.value,
            image = _imageUrl.value,
        )
    )

    fun setName(newValue: String) {
        viewModelScope.launch {
            _name.emit(newValue)
            _updatedUser.value = _updatedUser.value.copy(firstName = newValue)
            doneButtonEnabled()
        }
    }

    fun setSurname(newValue: String) {
        viewModelScope.launch {
            _surname.emit(newValue)
            _updatedUser.value = _updatedUser.value.copy(lastName = newValue)
            doneButtonEnabled()
        }
    }

    fun uploadImage(newValue: Uri?) {
        viewModelScope.launch { _imageUri.emit(newValue) }
        _doneButtonEnableState.value = true
    }

    private fun doneButtonEnabled() {
        _doneButtonEnableState.value = _updatedUser.value != _currentUser.value
    }

    fun editProfile(context: Context) {
        viewModelScope.launch(Dispatchers.IO) {
            val image = imageEncodeUseCase.invoke(_imageUri.value, context)
            editUserProfileUseCase.invoke(
                UpdateProfileInput(
                    firstName = _updatedUser.value.firstName,
                    lastName = _updatedUser.value.lastName,
                    image = image,
                )
            ).collect {
                when (it) {
                    is com.simply.birthdayapp.core.result.Result.Error -> _screenUiState.emit(
                        EditProfileUiState.Error(it.message)
                    )

                    is com.simply.birthdayapp.core.result.Result.Loading -> {
                        _screenUiState.emit(EditProfileUiState.Loading)
                    }

                    is com.simply.birthdayapp.core.result.Result.Success -> {
                        _screenUiState.emit(EditProfileUiState.Success)
                    }
                }
            }
        }
    }
}