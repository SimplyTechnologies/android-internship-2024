package com.simply.birthdayapp.main.shop.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.simply.birthdayapp.main.shop.domain.model.ShopDomainModel
import com.simply.birthdayapp.main.shop.domain.usecase.GetShopsUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ShopViewModel(private val getShopsUseCase: GetShopsUseCase) : ViewModel() {
    private val _shops = MutableStateFlow<List<ShopDomainModel>>(emptyList())
    val shops = _shops.asStateFlow()

    init {
        loadShops()
    }

    private fun loadShops() {
        viewModelScope.launch(Dispatchers.IO) {
            getShopsUseCase.invoke().collect { shops ->
                _shops.value = shops
            }
        }
    }
}