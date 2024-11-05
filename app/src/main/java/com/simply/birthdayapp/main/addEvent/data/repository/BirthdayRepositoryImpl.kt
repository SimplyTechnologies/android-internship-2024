package com.simply.birthdayapp.main.addEvent.data.repository

import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.api.Optional
import com.simply.CreateBirthdayMutation
import com.simply.birthdayapp.commondomain.model.Birthday
import com.simply.birthdayapp.core.result.Result
import com.simply.birthdayapp.main.addEvent.data.mapper.toBirthday
import com.simply.birthdayapp.main.addEvent.data.mapper.toCreateBirthdayInputData
import com.simply.birthdayapp.main.addEvent.data.model.BirthdayDataModel
import com.simply.birthdayapp.main.addEvent.domain.model.CreateBirthdayInputDomain
import com.simply.birthdayapp.main.addEvent.domain.repository.BirthDayRepository
import com.simply.type.CreateBirthdayInput
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class BirthdayRepositoryImpl(
    private val apolloClient: ApolloClient
) : BirthDayRepository {

    override fun createBirthday(input: CreateBirthdayInputDomain): Flow<Result<Birthday>> = flow {
        emit(Result.Loading(Birthday()))
        val inputModel = input.toCreateBirthdayInputData()
        val response = apolloClient.mutation(
            CreateBirthdayMutation(
                CreateBirthdayInput(
                    date = inputModel.date,
                    image = Optional.presentIfNotNull(inputModel.image),
                    message = Optional.presentIfNotNull(inputModel.message),
                    name = inputModel.name,
                    relation = inputModel.relation
                )
            )
        ).execute()

        val birthdayData = response.data?.createBirthday
        if (birthdayData != null) {
            val birthday = BirthdayDataModel(
                createdAt = birthdayData.createdAt.toString(),
                date = birthdayData.date.toString(),
                id = birthdayData.id,
                image = birthdayData.image,
                message = birthdayData.message,
                name = birthdayData.name,
                relation = birthdayData.relation,
                upcomingAge = birthdayData.upcomingAge,
                updatedAt = birthdayData.updatedAt.toString(),
                upcomingBirthday = birthdayData.upcomingBirthday.toString(),
                userId = birthdayData.userId
            ).toBirthday()

            emit(Result.Success(birthday))
        } else {
            emit(Result.Error(response.errors?.firstOrNull()?.message ?: "", Birthday()))
        }
    }.catch { e ->
        emit(Result.Error(e.message ?: "", Birthday()))
    }.flowOn(Dispatchers.IO)
}