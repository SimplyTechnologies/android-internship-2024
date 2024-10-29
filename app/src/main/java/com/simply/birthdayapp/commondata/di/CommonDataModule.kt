package com.simply.birthdayapp.commondata.di

import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.network.okHttpClient
import com.simply.birthdayapp.BuildConfig
import com.simply.birthdayapp.commondata.local.DataStoreProviderImpl
import com.simply.birthdayapp.commondata.local.TokenProvider
import com.simply.birthdayapp.commondata.network.interceptor.AuthInterceptor
import com.simply.birthdayapp.commondomain.local.DataStoreProvider
import okhttp3.OkHttpClient
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module


val commonDataModule = module {
    singleOf(::DataStoreProviderImpl) { bind<DataStoreProvider>() }
    singleOf(::TokenProvider)

    single<ApolloClient> {
        val authInterceptor = AuthInterceptor(get())
        ApolloClient.Builder()
            .serverUrl(BuildConfig.API_URL)
            .okHttpClient(
                OkHttpClient.Builder()
                    .addInterceptor(authInterceptor)
                    .build()
            )
            .build()
    }
}
