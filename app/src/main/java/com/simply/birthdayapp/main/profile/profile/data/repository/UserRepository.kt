package com.simply.birthdayapp.main.profile.profile.data.repository

import com.apollographql.apollo.ApolloClient
import com.simply.GetUserProfileQuery
import com.simply.birthdayapp.core.ErrorMessages.GENERAL_ERROR
import com.simply.birthdayapp.core.ErrorMessages.WRONG_CREDENTIALS
import com.simply.birthdayapp.core.result.Result
import com.simply.birthdayapp.main.profile.profile.data.mapper.ProfileMapper
import com.simply.birthdayapp.main.profile.profile.data.model.UserData
import com.simply.birthdayapp.main.profile.profile.domain.model.UserDomain
import com.simply.birthdayapp.main.profile.profile.domain.repository.UserProfileRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class UserProfileRepositoryImpl(private val apolloClient: ApolloClient) : UserProfileRepository {
    override fun getUserProfile(): Flow<Result<UserDomain>> = flow {
        emit(Result.Loading(data = UserDomain.default))
        try {
            val response = apolloClient.query(GetUserProfileQuery()).execute()

            if (response.hasErrors()) {
                val errorMessage = response.errors?.firstOrNull()?.message ?: GENERAL_ERROR
                emit(
                    Result.Error(errorMessage, data = UserDomain.default)
                )
            } else {
                val profileData = response.data?.profile
                if (profileData != null) {
                    emit(
                        Result.Success(
                            ProfileMapper.toDomain(
                                UserData(
                                    id = profileData.id,
                                    email = profileData.email,
                                    firstName = profileData.firstName,
                                    lastName = profileData.lastName,
                                    image = profileData.image
                                )
                            )
                        )
                    )
                } else {
                    emit(Result.Error(WRONG_CREDENTIALS, data = UserDomain.default))
                }
            }
        } catch (e: Exception) {
            emit(
                Result.Error(
                    e.localizedMessage ?: GENERAL_ERROR, data = UserDomain.default
                )
            )
        }
    }
}