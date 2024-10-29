package com.simply.birthdayapp.main.shop.domain.usecase

import com.simply.birthdayapp.main.shop.domain.model.ShopDomainModel
import com.simply.birthdayapp.main.shop.domain.repository.ShopRepository
import kotlinx.coroutines.flow.Flow

interface GetShopsUseCase {
    suspend fun invoke(): Flow<List<ShopDomainModel>>
}

class GetShopsUseCaseImpl(private val repository: ShopRepository) :
    GetShopsUseCase {
    override suspend fun invoke() = repository.getShops()
}