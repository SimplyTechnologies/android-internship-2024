package com.simply.birthdayapp.main.shop.domain.usecase

import com.simply.birthdayapp.core.result.Result
import com.simply.birthdayapp.main.shop.domain.model.ShopDomainModel
import com.simply.birthdayapp.main.shop.domain.repository.ShopRepository
import kotlinx.coroutines.flow.Flow

interface GetShopsUseCase {
    fun invoke(): Flow<Result<List<ShopDomainModel>>>
}

class GetShopsUseCaseImpl(private val repository: ShopRepository) :
    GetShopsUseCase {
    override fun invoke() = repository.getShops()
}