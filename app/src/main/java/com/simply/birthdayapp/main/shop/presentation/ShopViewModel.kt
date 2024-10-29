package com.simply.birthdayapp.main.shop.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.simply.birthdayapp.core.result.Result
import com.simply.birthdayapp.main.shop.domain.usecase.GetShopsUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class ShopViewModel(private val getShopsUseCase: GetShopsUseCase) : ViewModel() {

    init {
        fetchShops()
    }

    private val _shopsUiState = MutableStateFlow<ShopListUiState>(ShopListUiState.Loading)
    val shopsUiState: StateFlow<ShopListUiState> = _shopsUiState.asStateFlow()

    private fun fetchShops() {
        viewModelScope.launch(Dispatchers.IO) {
            getShopsUseCase.invoke().collectLatest {
                val state = when (it) {
                    is Result.Error -> ShopListUiState.Error(it.message)
                    is Result.Success -> ShopListUiState.Success(it.data)
                    is Result.Loading -> ShopListUiState.Loading
                }
                _shopsUiState.emit(state)
            }
        }
    }
}