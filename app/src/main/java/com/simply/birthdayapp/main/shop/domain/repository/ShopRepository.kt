package com.simply.birthdayapp.main.shop.domain.repository

import com.simply.birthdayapp.main.shop.domain.model.ShopDomainModel
import kotlinx.coroutines.flow.Flow
import com.simply.birthdayapp.core.result.Result

interface ShopRepository {
    fun getShops(): Flow<Result<List<ShopDomainModel>>>
}