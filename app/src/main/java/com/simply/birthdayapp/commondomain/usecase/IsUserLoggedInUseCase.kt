package com.simply.birthdayapp.commondomain.usecase

import com.simply.birthdayapp.commondomain.local.DataStoreProvider
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

interface IsUserLoggedInUseCase {
     operator fun invoke(): Flow<Boolean>
}

class IsUserLoggedInUseCaseImpl(private val dataStoreProvider: DataStoreProvider) :
    IsUserLoggedInUseCase {
    override operator fun invoke() = dataStoreProvider.getToken().map { it.isNotEmpty() }
}
