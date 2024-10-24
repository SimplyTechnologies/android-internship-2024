package com.simply.birthdayapp.auth.registration.data.repository

import android.util.Log
import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.api.ApolloResponse
import com.simply.SignUpMutation
import com.simply.birthdayapp.auth.registration.domain.model.SignUpInput
import com.simply.birthdayapp.auth.registration.domain.model.User
import com.simply.birthdayapp.auth.registration.domain.repository.SignUpRepository
import com.simply.birthdayapp.core.result.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


class SignUpRepositoryImpl(private val apolloClient: ApolloClient) : SignUpRepository {

    override suspend fun signUp(signUpInput: SignUpInput): Result<User> {
        return withContext(Dispatchers.IO) {
            try {
                Result.Loading<Nothing>("")
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
                    User(
                        id = it.id,
                        email = it.email,
                        firstName = it.firstName,
                        lastName = it.lastName,
                        image = it.image
                    )
                }

                if (response.hasErrors()) {
                    Log.d("ERROR", response.errors.toString())
                }

                if (user == null) {

                    Result.Error(message = "User already exists")
                } else {
                    Result.Success(user)
                }

            } catch (e: Exception) {
                Result.Error("Sorry, an error occurred registering your account, please try again.")
            }
        }
    }
}