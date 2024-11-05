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
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.util.Calendar
import java.util.Date

class AddEventViewModel(
    private val createBirthdayUseCase: CreateBirthdayUseCase,
    private val imageEncodeUseCase: ImageEncodeUseCase,
) : ViewModel() {

    private val _name = MutableStateFlow("")
    val name: StateFlow<String> = _name.asStateFlow()

    private val _relationship = MutableStateFlow("")
    val relationship: StateFlow<String> = _relationship.asStateFlow()

    private val _familyRelation = MutableStateFlow(FamilyRelation.getDisplayNames())
    val familyRelation: StateFlow<List<String>> = _familyRelation.asStateFlow()

    private val _selectedDay = MutableStateFlow(Calendar.getInstance().get(Calendar.DAY_OF_MONTH))
    val selectedDay: StateFlow<Int> = _selectedDay.asStateFlow()

    private val _selectedMonth = MutableStateFlow(Calendar.getInstance().get(Calendar.MONTH) + 1)
    val selectedMonth: StateFlow<Int> = _selectedMonth.asStateFlow()

    private val _selectedYear = MutableStateFlow(Calendar.getInstance().get(Calendar.YEAR))
    val selectedYear: StateFlow<Int> = _selectedYear.asStateFlow()

    private val _isAddRelation = MutableStateFlow(false)
    val isAddRelation: StateFlow<Boolean> = _isAddRelation.asStateFlow()

    private val _newRelation = MutableStateFlow("")
    val newRelation: StateFlow<String> = _newRelation.asStateFlow()

    private val _addEventUiState = MutableStateFlow<AddEventUiState?>(null)
    val addEventUiState: StateFlow<AddEventUiState?> = _addEventUiState.asStateFlow()

    private val _imageSource = MutableStateFlow<ImageSource>(ImageSource.Unknown)
    val imageSource: StateFlow<ImageSource> = _imageSource.asStateFlow()

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
            val image = imageEncode(context)
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

    fun resetState() {
        _addEventUiState.value = null
    }
}