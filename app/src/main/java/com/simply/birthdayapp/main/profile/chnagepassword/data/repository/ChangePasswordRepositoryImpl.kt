package com.simply.birthdayapp.main.profile.chnagepassword.data.repository

import com.apollographql.apollo.ApolloClient
import com.simply.ChangePasswordMutation
import com.simply.birthdayapp.R
import com.simply.birthdayapp.core.result.Result
import com.simply.birthdayapp.core.result.Result.Error
import com.simply.birthdayapp.main.profile.chnagepassword.domain.model.ChangePasswordInput
import com.simply.birthdayapp.main.profile.chnagepassword.domain.repository.ChangePasswordRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn


class ChangePasswordRepositoryImpl(
    private val apolloClient: ApolloClient

) : ChangePasswordRepository {
    override fun changePassword(input: ChangePasswordInput): Flow<Result<Boolean>> = flow {
        emit(Result.Loading(false))
        val mutation = ChangePasswordMutation(
            oldPassword = input.oldPassword,
            newPassword = input.newPassword,
        )

        val response = apolloClient.mutation(mutation).execute()

        if (response.data?.changePassword == true) {
            this.emit(Result.Success(true))
        } else {
            emit(Error(R.string.general_error.toString(), false))
        }
    }.catch {
        emit(Error(R.string.general_error.toString(), false))
    }.flowOn(Dispatchers.IO)
}
