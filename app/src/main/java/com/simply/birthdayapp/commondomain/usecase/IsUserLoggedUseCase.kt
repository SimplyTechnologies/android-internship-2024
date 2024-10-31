package com.simply.birthdayapp.commondomain.usecase

import com.simply.birthdayapp.commondomain.local.DataStoreProvider
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

interface IsUserLoggedUseCase {
     operator fun invoke(): Flow<Boolean>
}

class IsUserLoggedUseCaseImpl(private val dataStoreProvider: DataStoreProvider) :
    IsUserLoggedUseCase {
    override operator fun invoke() = dataStoreProvider.getToken().map { it.isNotEmpty() }
}
