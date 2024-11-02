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
import kotlinx.coroutines.withContext

class BirthdayRepositoryImpl(
    private val apolloClient: ApolloClient
) : BirthDayRepository {

    override suspend fun createBirthday(input: CreateBirthdayInputDomain): Result<Birthday> {
        return withContext(Dispatchers.IO) {
            Result.Loading(Birthday(
                "",
                "",
                0,
                "",
                "",
                "",
                "",
                0,
                "",
                "",
                0
            ))
            val response = apolloClient.mutation(
                CreateBirthdayMutation(
                    CreateBirthdayInput(
                        date = input.toCreateBirthdayInputData().date,
                        image = Optional.presentIfNotNull(input.toCreateBirthdayInputData().image),
                        message = Optional.presentIfNotNull(input.toCreateBirthdayInputData().message),
                        name = input.toCreateBirthdayInputData().name,
                        relation = input.toCreateBirthdayInputData().relation
                    )
                )
            ).execute()

            val birthdayData = response.data
            val birthday = birthdayData?.createBirthday?.let {
                BirthdayDataModel(
                    createdAt = it.createdAt.toString(),
                    date = it.date.toString(),
                    id = it.id,
                    image = it.image,
                    message = it.message,
                    name = it.name,
                    relation = it.relation,
                    upcomingAge = it.upcomingAge,
                    updatedAt = it.updatedAt.toString(),
                    upcomingBirthday = it.upcomingBirthday.toString(),
                    userId = it.userId
                ).toBirthday()
            }
            if (birthday != null) {
                Result.Success(birthday)
            } else {
                Result.Error(
                    message = response.errors?.firstOrNull()?.message ?: "An unexpected error occurred", data = Birthday(
                        "",
                        "",
                        0,
                        "",
                        "",
                        "",
                        "",
                        0,
                        "",
                        "",
                        0
                    )
                )
            }
        }
    }
}
