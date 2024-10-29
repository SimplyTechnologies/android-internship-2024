package com.simply.birthdayapp.main.shop.data.repository

import com.simply.birthdayapp.main.shop.data.helper.ShopHelper
import com.simply.birthdayapp.main.shop.data.mapper.toDomainModel
import com.simply.birthdayapp.main.shop.domain.model.ShopDomainModel
import com.simply.birthdayapp.main.shop.domain.repository.ShopRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

class ShopRepositoryImpl(private val shopHelper: ShopHelper) : ShopRepository {
    override suspend fun getShops(): Flow<List<ShopDomainModel>> {
        return withContext(context = Dispatchers.IO) {
            shopHelper.getShopList().map { flow -> flow.map { it.toDomainModel() } }
        }
    }
}