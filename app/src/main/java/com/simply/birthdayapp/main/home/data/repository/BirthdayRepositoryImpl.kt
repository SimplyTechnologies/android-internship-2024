package com.simply.birthdayapp.main.home.data.repository

import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.api.ApolloResponse
import com.simply.GetBirthdaysQuery
import com.simply.birthdayapp.commondomain.model.Birthday
import com.simply.birthdayapp.main.home.data.mapper.BirthdayMapper
import com.simply.birthdayapp.main.home.domain.repository.BirthdayRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class BirthdayRepositoryImpl(private val apolloClient: ApolloClient) : BirthdayRepository {
    override fun getBirthdays(): Flow<List<Birthday>> = flow {
        val response: ApolloResponse<GetBirthdaysQuery.Data> =
            apolloClient.query(GetBirthdaysQuery()).execute()

        val birthdays: List<Birthday> =
            response.data?.birthdays?.let { BirthdayMapper.mapToDomainList(it) }
                ?: emptyList() /*getMockBirthdays()*/

        emit(birthdays)
    }
}