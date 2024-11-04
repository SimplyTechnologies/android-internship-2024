package com.simply.birthdayapp.main.addEvent.presentation

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.simply.birthdayapp.commonpresentation.components.image.ImageSource
import com.simply.birthdayapp.main.addEvent.domain.FamilyRelation
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.util.Calendar

class AddEventViewModel : ViewModel() {

    private val _name = MutableStateFlow("")
    val name: StateFlow<String> = _name.asStateFlow()

    private val _relationship = MutableStateFlow("")
    val relationship: StateFlow<String> = _relationship.asStateFlow()

    private val _familyRelation = MutableStateFlow(FamilyRelation.getDisplayNames())
    val familyRelation: StateFlow<List<String>> = _familyRelation.asStateFlow()

    private val _selectedDay = MutableStateFlow(Calendar.getInstance().get(Calendar.DAY_OF_MONTH))
    val selectedDay: StateFlow<Int> = _selectedDay.asStateFlow()

    private val _selectedMonth = MutableStateFlow(Calendar.getInstance().get(Calendar.MONTH))
    val selectedMonth: StateFlow<Int> = _selectedMonth.asStateFlow()

    private val _selectedYear = MutableStateFlow(Calendar.getInstance().get(Calendar.YEAR))
    val selectedYear: StateFlow<Int> = _selectedYear.asStateFlow()

    private val _isAddRelation = MutableStateFlow(false)
    val isAddRelation: StateFlow<Boolean> = _isAddRelation.asStateFlow()

    private val _newRelation = MutableStateFlow("")
    val newRelation: StateFlow<String> = _newRelation.asStateFlow()

    private val _imageSource = MutableStateFlow<ImageSource>(ImageSource.Unknown)
    val imageSource: StateFlow<ImageSource> = _imageSource.asStateFlow()

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

    fun setImageUrl(newValue: Uri?) {
        viewModelScope.launch {
            _imageSource.value = if (newValue != null) {
                ImageSource.Uri(newValue.toString())
            } else {
                ImageSource.Unknown
            }
        }
    }
}