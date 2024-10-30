package com.simply.birthdayapp.main.profile.chnagepassword.domain.usecase

import com.simply.birthdayapp.commondomain.local.ClearTokenDataStoreProvider

interface ClearAccessTokenUseCase {
    suspend fun invoke()
}

class ClearAccessTokenUseCaseImpl(
    private val dataStoreProvider: ClearTokenDataStoreProvider
) : ClearAccessTokenUseCase {
    override suspend fun invoke() = dataStoreProvider.clearToken()
}