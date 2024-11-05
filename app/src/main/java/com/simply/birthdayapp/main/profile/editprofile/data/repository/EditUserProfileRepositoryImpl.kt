package com.simply.birthdayapp.main.profile.editprofile.data.repository

import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.api.Optional
import com.simply.EditUserProfileMutation
import com.simply.birthdayapp.core.ErrorMessages
import com.simply.birthdayapp.core.result.Result
import com.simply.birthdayapp.main.profile.editprofile.domain.model.UpdateProfileInput
import com.simply.birthdayapp.main.profile.editprofile.domain.repository.EditUserProfileRepository
import com.simply.birthdayapp.main.profile.profile.domain.model.UserDomain
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class EditUserProfileRepositoryImpl(
    private val apolloClient: ApolloClient,
) : EditUserProfileRepository {

    override fun updateProfile(updateProfileInput: UpdateProfileInput): Flow<Result<UserDomain>> =
        flow {
            emit(Result.Loading(UserDomain.default))

            val response = apolloClient.mutation(
                EditUserProfileMutation(
                    com.simply.type.UpdateProfileInput(
                        firstName = Optional.presentIfNotNull(updateProfileInput.firstName),
                        lastName = Optional.presentIfNotNull(updateProfileInput.lastName),
                        image = Optional.presentIfNotNull(updateProfileInput.image)
                    )
                )
            ).execute()

            if (response.hasErrors()) {
                val errorMessage =
                    response.errors?.firstOrNull()?.message ?: ErrorMessages.GENERAL_ERROR
                emit(Result.Error(errorMessage, UserDomain.default))
            } else {
                emit(Result.Success(UserDomain.default))
            }
        }.catch { e ->
            emit(
                Result.Error(
                    "${e.message}", UserDomain.default
                )
            )
        }.flowOn(Dispatchers.IO)
}
