package com.simply.birthdayapp.auth.signIn.data.repository

import com.apollographql.apollo.ApolloClient
import com.simply.LoginMutation
import com.simply.birthdayapp.auth.signIn.domain.model.LoginInput
import com.simply.birthdayapp.auth.signIn.domain.repository.SignInRepository
import com.simply.birthdayapp.commondomain.local.DataStoreProvider
import com.simply.birthdayapp.core.result.Result

class SignInRepositoryImpl(
    private val dataStoreProvider: DataStoreProvider,
    private val apolloClient: ApolloClient
) : SignInRepository {

    override suspend fun setSignedIn(isSignedIn: Boolean) =
        dataStoreProvider.setSignedIn(isSignedIn)

    override suspend fun saveAccessToken(token: String) {
        dataStoreProvider.saveAccessToken(token)
    }

    override suspend fun login(loginInput: LoginInput): Result<String> {
        return try {

            val response = apolloClient.mutation(
                LoginMutation(
                    email = loginInput.email,
                    password = loginInput.password
                )
            ).execute()

            val accessToken = response.data?.login?.accessToken
            if (accessToken != null) {
                Result.Success(accessToken)
            } else {
                Result.Error("We could not find an account with a set email or password. Please check the entered credentials.")
            }

        } catch (e: Exception) {
            Result.Error("${e.message}")
        }
    }
}