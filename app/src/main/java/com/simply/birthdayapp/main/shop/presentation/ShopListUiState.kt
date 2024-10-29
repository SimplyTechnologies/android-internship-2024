package com.simply.birthdayapp.main.shop.presentation

import com.simply.birthdayapp.main.shop.domain.model.ShopDomainModel

sealed class ShopListUiState {
    data object Loading : ShopListUiState()
    data class Success(val data: List<ShopDomainModel>) : ShopListUiState()
    data class Error(val message: String) : ShopListUiState()
}