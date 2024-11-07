package com.simply.birthdayapp.main.addEvent.presentation

import android.content.Context
import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.simply.birthdayapp.commondomain.usecase.ImageEncodeUseCase
import com.simply.birthdayapp.commonpresentation.components.image.ImageSource
import com.simply.birthdayapp.core.result.Result
import com.simply.birthdayapp.main.addEvent.domain.FamilyRelation
import com.simply.birthdayapp.main.addEvent.domain.model.CreateBirthdayInputDomain
import com.simply.birthdayapp.main.addEvent.domain.usecase.CreateBirthdayUseCase
import com.simply.birthdayapp.main.addEvent.domain.usecase.DeleteBirthdayUseCase
import com.simply.birthdayapp.main.addEvent.domain.usecase.UpdateBirthdayUseCase
import com.simply.birthdayapp.main.home.presentation.components.formatDate
import com.simply.birthdayapp.main.navigation.BirthdayMode
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.util.Calendar
import java.util.Date

class AddEventViewModel(
    val birthdayMode: BirthdayMode,
    private val createBirthdayUseCase: CreateBirthdayUseCase,
    private val imageEncodeUseCase: ImageEncodeUseCase,
    private val updateBirthdayUseCase: UpdateBirthdayUseCase,
    private val deleteBirthdayUseCase: DeleteBirthdayUseCase
) : ViewModel() {

    private val _showDialog = MutableStateFlow(false)
    val showDialog: StateFlow<Boolean> = _showDialog.asStateFlow()

    private val _name = MutableStateFlow(birthdayMode.birthday.name)
    val name: StateFlow<String> = _name.asStateFlow()

    private val _relationship = MutableStateFlow(birthdayMode.birthday.relation)
    val relationship: StateFlow<String> = _relationship.asStateFlow()

    private val _familyRelation = MutableStateFlow(
        if (birthdayMode.birthday.relation.isNotEmpty() && !FamilyRelation.getDisplayNames()
                .contains(birthdayMode.birthday.relation)
        ) {
            val newList = FamilyRelation.getDisplayNames()
            newList.add(birthdayMode.birthday.relation)
            newList
        } else {
            FamilyRelation.getDisplayNames()
        }
    )
    val familyRelation: StateFlow<List<String>> = _familyRelation.asStateFlow()

    private val _selectedDay = MutableStateFlow(
        if (birthdayMode.birthday.date.isEmpty()) {
            Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
        } else {
            formatDate(birthdayMode.birthday.date).split(".")[0].toInt()
        }
    )
    val selectedDay: StateFlow<Int> = _selectedDay.asStateFlow()

    private val _selectedMonth = MutableStateFlow(
        if (birthdayMode.birthday.date.isEmpty()) {
            Calendar.getInstance().get(Calendar.MONTH) + 1
        } else {
            formatDate(birthdayMode.birthday.date).split(".")[1].toInt()
        }
    )
    val selectedMonth: StateFlow<Int> = _selectedMonth.asStateFlow()

    private val _selectedYear = MutableStateFlow(
        if (birthdayMode.birthday.date.isEmpty()) {
            Calendar.getInstance().get(Calendar.YEAR)
        } else {
            formatDate(birthdayMode.birthday.date).split(".")[2].toInt()
        }
    )
    val selectedYear: StateFlow<Int> = _selectedYear.asStateFlow()

    private val _isAddRelation = MutableStateFlow(false)
    val isAddRelation: StateFlow<Boolean> = _isAddRelation.asStateFlow()

    private val _newRelation = MutableStateFlow("")
    val newRelation: StateFlow<String> = _newRelation.asStateFlow()

    private val _addEventUiState = MutableStateFlow<AddEventUiState?>(null)
    val addEventUiState: StateFlow<AddEventUiState?> = _addEventUiState.asStateFlow()

    private val _imageSource = MutableStateFlow<ImageSource>(
        if (birthdayMode.birthday.image != null) {
            ImageSource.Url(birthdayMode.birthday.image)
        } else {
            ImageSource.Unknown
        }
    )
    val imageSource: StateFlow<ImageSource> = _imageSource.asStateFlow()

    fun setShowDialog(newValue: Boolean) {
        viewModelScope.launch {
            _showDialog.emit(newValue)
        }
    }

    fun imageEncode(context: Context): String? =
        imageSource.value.source?.let { imageEncodeUseCase.invoke(it, context) }

    fun setIsAddRelation(newValue: Boolean) {
        viewModelScope.launch {
            _isAddRelation.emit(newValue)
        }
    }

    fun setNewRelation(newValue: String) {
        viewModelScope.launch {
            _newRelation.emit(newValue)
        }
    }

    fun setSelectedDay(newValue: Int) {
        viewModelScope.launch {
            _selectedDay.emit(newValue)
        }
    }

    fun setSelectedMonth(newValue: Int) {
        viewModelScope.launch {
            _selectedMonth.emit(newValue)
        }
    }

    fun setSelectedYear(newValue: Int) {
        viewModelScope.launch {
            _selectedYear.emit(newValue)
        }
    }

    fun setName(newValue: String) {
        viewModelScope.launch {
            _name.emit(newValue)
        }
    }

    fun setRelationship(newValue: String) {
        viewModelScope.launch {
            _relationship.emit(newValue)
        }
    }

    fun setFamilyRelation(newValue: String) {
        viewModelScope.launch {
            _familyRelation.value.add(newValue)
            _familyRelation.emit(_familyRelation.value)
        }
    }

    fun setImageUri(newValue: Uri?) {
        viewModelScope.launch {
            _imageSource.value = if (newValue != null) {
                ImageSource.Uri(newValue.toString())
            } else {
                ImageSource.Unknown
            }
        }
    }

    private fun createDateFromSelectedValues(
        selectedYear: Int,
        selectedMonth: Int,
        selectedDay: Int
    ): Date {
        val calendar = Calendar.getInstance().apply {
            set(Calendar.YEAR, selectedYear)
            set(Calendar.MONTH, selectedMonth - 1)
            set(Calendar.DAY_OF_MONTH, selectedDay)
            set(Calendar.HOUR_OF_DAY, Calendar.getInstance().get(Calendar.HOUR_OF_DAY))
            set(Calendar.MINUTE, Calendar.getInstance().get(Calendar.MINUTE))
            set(Calendar.SECOND, Calendar.getInstance().get(Calendar.SECOND))
            set(Calendar.MILLISECOND, Calendar.getInstance().get(Calendar.MILLISECOND))
        }
        return calendar.time
    }

    fun addEvent(context: Context) {
        viewModelScope.launch {
            val image = birthdayMode.birthday.image ?: imageEncode(context)
            _addEventUiState.value = AddEventUiState.Loading
            val selectedDate = createDateFromSelectedValues(
                _selectedYear.value,
                _selectedMonth.value,
                _selectedDay.value
            )
            createBirthdayUseCase.invoke(
                CreateBirthdayInputDomain(
                    date = selectedDate,
                    image = image,
                    message = "",
                    name = _name.value,
                    relation = _relationship.value
                )
            ).collect {
                _addEventUiState.value = when (it) {
                    is Result.Success -> {
                        AddEventUiState.Success(it.data.toString())
                    }

                    is Result.Error -> {
                        AddEventUiState.Error(it.message)
                    }

                    is Result.Loading -> {
                        AddEventUiState.Loading
                    }
                }
            }
        }
    }

    fun updateEvent(context: Context) {
        viewModelScope.launch {
            val image = birthdayMode.birthday.image ?: imageEncode(context)
            _addEventUiState.value = AddEventUiState.Loading
            val selectedDate = createDateFromSelectedValues(
                _selectedYear.value,
                _selectedMonth.value,
                _selectedDay.value
            )
            updateBirthdayUseCase.invoke(
                birthdayMode.birthday.id,
                CreateBirthdayInputDomain(
                    date = selectedDate,
                    image = image,
                    message = birthdayMode.birthday.message,
                    name = _name.value,
                    relation = _relationship.value
                )
            ).collect {
                _addEventUiState.value = when (it) {
                    is Result.Success -> {
                        AddEventUiState.Success(it.data.toString())
                    }

                    is Result.Error -> {
                        AddEventUiState.Error(it.message)
                    }

                    is Result.Loading -> {
                        AddEventUiState.Loading
                    }
                }
            }
        }

    }

    fun deleteEvent() {
        viewModelScope.launch {
            deleteBirthdayUseCase.invoke(birthdayMode.birthday.id).collect {
                _addEventUiState.value = when (it) {
                    is Result.Success -> {
                        AddEventUiState.Success(it.data.toString())
                    }

                    is Result.Error -> {
                        AddEventUiState.Error(it.message)
                    }

                    is Result.Loading -> {
                        AddEventUiState.Loading
                    }
                }
            }
        }
    }

    fun resetState() {
        _addEventUiState.value = null
    }
}