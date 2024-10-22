package com.simply.birthdayapp.commondata.di

import com.apollographql.apollo.ApolloClient
import com.simply.birthdayapp.BuildConfig
import org.koin.dsl.module

val commonDataModule = module {
    single<ApolloClient> {
        ApolloClient.Builder()
            .serverUrl(BuildConfig.API_URL)
            .build()
    }
}