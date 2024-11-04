package com.simply.birthdayapp.main.shop.data.repository

import com.apollographql.apollo.ApolloClient
import com.simply.GetShopsQuery
import com.simply.birthdayapp.core.ErrorMessages.GENERAL_ERROR
import com.simply.birthdayapp.core.result.Result
import com.simply.birthdayapp.main.shop.data.mapper.toDomainModel
import com.simply.birthdayapp.main.shop.data.model.ShopDataModel
import com.simply.birthdayapp.main.shop.domain.model.ShopDomainModel
import com.simply.birthdayapp.main.shop.domain.repository.ShopRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class ShopRepositoryImpl(private val apolloClient: ApolloClient) : ShopRepository {
    override fun getShops(): Flow<Result<List<ShopDomainModel>>> = flow {
        val response = apolloClient.query(GetShopsQuery()).execute()
        if (response.hasErrors()) {
            val errorMessage = response.errors?.firstOrNull()?.message ?: GENERAL_ERROR
            emit(
                Result.Error(errorMessage, data = emptyList())
            )
        } else {
            val shops = response.data?.shops?.map { shop ->
                ShopDataModel(
                    id = shop.id,
                    name = shop.name,
                    avatarUrl = shop.image,
                    phone = shop.phone,
                    address = shop.address,
                    siteUrl = shop.url,
                    rate = shop.rate
                ).toDomainModel()
            } ?: emptyList()
            emit(Result.Success(shops))
        }
    }
}