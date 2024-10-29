package com.simply.birthdayapp.main.shop.domain.repository

import com.simply.birthdayapp.main.shop.domain.model.ShopDomainModel
import kotlinx.coroutines.flow.Flow

interface ShopRepository {
    suspend fun getShops(): Flow<List<ShopDomainModel>>
}