package com.simply.birthdayapp.auth.signIn.data.repository

import com.apollographql.apollo.ApolloClient
import com.simply.LoginMutation
import com.simply.birthdayapp.auth.signIn.data.mapper.toLoginDataModel
import com.simply.birthdayapp.auth.signIn.domain.model.LoginInputDomain
import com.simply.birthdayapp.auth.signIn.domain.repository.SignInRepository
import com.simply.birthdayapp.commondomain.local.DataStoreProvider
import com.simply.birthdayapp.core.result.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class SignInRepositoryImpl(
    private val dataStoreProvider: DataStoreProvider, private val apolloClient: ApolloClient
) : SignInRepository {

    override suspend fun setSignedIn(isSignedIn: Boolean) =
        dataStoreProvider.setSignedIn(isSignedIn)

    override suspend fun saveAccessToken(token: String) {
        dataStoreProvider.saveAccessToken(token)
    }

    override suspend fun login(loginInput: LoginInputDomain): Result<String> {
        return withContext(Dispatchers.IO) {
            Result.Loading(data = "")
            val response = apolloClient.mutation(
                LoginMutation(
                    email = loginInput.toLoginDataModel().email,
                    password = loginInput.toLoginDataModel().password
                )
            ).execute()

            val accessToken = response.data?.login?.accessToken
            if (accessToken != null) {
                Result.Success(accessToken)
            } else {
                Result.Error(data = "", message = response.errors?.first()?.message ?: "")
            }
        }
    }
}