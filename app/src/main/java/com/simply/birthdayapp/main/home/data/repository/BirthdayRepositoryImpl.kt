package com.simply.birthdayapp.main.home.data.repository

import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.api.ApolloResponse
import com.simply.GetBirthdaysQuery
import com.simply.birthdayapp.commondomain.model.Birthday
import com.simply.birthdayapp.core.ErrorMessages
import com.simply.birthdayapp.core.result.Result
import com.simply.birthdayapp.main.home.data.mapper.BirthdayMapper
import com.simply.birthdayapp.main.home.domain.repository.BirthdayRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class BirthdayRepositoryImpl(private val apolloClient: ApolloClient) : BirthdayRepository {
    override fun getBirthdays() = flow {
        emit(Result.Loading(emptyList()))
        val response: ApolloResponse<GetBirthdaysQuery.Data> =
            apolloClient.query(GetBirthdaysQuery()).execute()

        val birthdays: List<Birthday>? =
            response.data?.birthdays?.let { BirthdayMapper.mapToDomainList(it) }

        if (response.hasErrors()) {
            emit(
                Result.Error(
                    message = response.errors?.firstOrNull()?.message
                        ?: ErrorMessages.GENERAL_ERROR,
                    data = emptyList()
                )
            )
        } else {
            emit(Result.Success(birthdays ?: emptyList()))
        }
    }.catch {
        emit(
            Result.Error(
                message = it.message ?: ErrorMessages.GENERAL_ERROR,
                data = emptyList()
            )
        )
    }.flowOn(Dispatchers.IO)
}