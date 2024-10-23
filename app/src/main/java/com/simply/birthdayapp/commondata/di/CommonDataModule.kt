package com.simply.birthdayapp.commondata.di

import com.apollographql.apollo.ApolloClient
import com.simply.birthdayapp.BuildConfig
import com.simply.birthdayapp.commondata.local.DataStoreProviderImpl
import com.simply.birthdayapp.commondomain.local.DataStoreProvider
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val commonDataModule = module {
    singleOf(::DataStoreProviderImpl) { bind<DataStoreProvider>() }
    single<ApolloClient> {
        ApolloClient.Builder()
            .serverUrl(BuildConfig.API_URL)
            .build()
    }
}