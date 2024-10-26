package com.simply.birthdayapp.auth.registration.data.repository

import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.api.ApolloResponse
import com.simply.SignUpMutation
import com.simply.birthdayapp.auth.registration.data.mapper.toUserDomainModel
import com.simply.birthdayapp.auth.registration.data.model.UserDataModel
import com.simply.birthdayapp.auth.registration.domain.model.SignUpInputDomainModel
import com.simply.birthdayapp.auth.registration.domain.model.UserDomainModel
import com.simply.birthdayapp.auth.registration.domain.repository.SignUpRepository
import com.simply.birthdayapp.core.result.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


class SignUpRepositoryImpl(
    private val apolloClient: ApolloClient
) : SignUpRepository {

    override suspend fun signUp(signUpInput: SignUpInputDomainModel): Result<UserDomainModel> {
        return withContext(Dispatchers.IO) {
            Result.Loading(data = Unit)
            val response: ApolloResponse<SignUpMutation.Data> = apolloClient.mutation(
                SignUpMutation(
                    com.simply.type.SignUpInput(
                        email = signUpInput.email,
                        firstName = signUpInput.firstName,
                        lastName = signUpInput.lastName,
                        password = signUpInput.password
                    )
                )
            ).execute()


            val user = response.data?.signUp?.let {
                UserDataModel(
                    id = it.id,
                    email = it.email,
                    firstName = it.firstName,
                    lastName = it.lastName,
                    image = it.image
                ).toUserDomainModel()
            }

            if (user != null) {
                Result.Success(user)
            } else {
                Result.Error(
                    message = response.errors?.first()?.message ?: "",
                    data = UserDomainModel(0, "", "", "", "")
                )
            }


        }
    }
}