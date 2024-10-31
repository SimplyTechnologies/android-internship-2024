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

class ShopViewModel(
    private val getShopsUseCase: GetShopsUseCase
) : ViewModel() {
    private val _shopsUiState = MutableStateFlow<ShopListUiState>(ShopListUiState.Loading)
    val shopsUiState: StateFlow<ShopListUiState> = _shopsUiState.asStateFlow()
    private val _searchText = MutableStateFlow("")
    val searchText: StateFlow<String> = _searchText.asStateFlow()

    init {
        fetchShops()
    }

    fun onClearSearch() {
        _searchText.value = ""
        fetchShops()
    }

    fun onSearchText(newText: String) {
        _searchText.value = newText
        fetchShops(newText)
    }

    private fun fetchShops(query: String = "") {
        viewModelScope.launch(Dispatchers.IO) {
            getShopsUseCase.invoke()
                .collectLatest { result ->
                    val state = when (result) {
                        is Result.Error -> ShopListUiState.Error(result.message)
                        is Result.Success -> {
                            val filteredShops = result.data.filter {
                                it.name.contains(query, ignoreCase = true)
                            }
                            ShopListUiState.Success(filteredShops)
                        }

                        is Result.Loading -> ShopListUiState.Loading
                    }
                    _shopsUiState.emit(state)
                }
        }
    }
}