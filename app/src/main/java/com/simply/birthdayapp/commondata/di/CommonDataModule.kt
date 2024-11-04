package com.simply.birthdayapp.commondata.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.network.okHttpClient
import com.simply.birthdayapp.BuildConfig
import com.simply.birthdayapp.commondata.ImageEncodeDecodeHelper
import com.simply.birthdayapp.commondata.local.DataStoreProviderImpl
import com.simply.birthdayapp.commondata.local.TokenProvider
import com.simply.birthdayapp.commondata.network.interceptor.AuthInterceptor
import com.simply.birthdayapp.commondomain.local.ClearTokenDataStoreProvider
import com.simply.birthdayapp.commondomain.local.DataStoreProvider
import com.simply.birthdayapp.commondomain.repository.ImageEncoderDecoderRepository
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "isSignedIn")

val commonDataModule = module {

    single { androidContext().dataStore }
    singleOf(::DataStoreProviderImpl) { bind<ClearTokenDataStoreProvider>() }
    singleOf(::DataStoreProviderImpl) { bind<DataStoreProvider>() }
    singleOf(::TokenProvider)
    singleOf(::ImageEncodeDecodeHelper) { bind<ImageEncoderDecoderRepository>() }

    single<ApolloClient> {
        val authInterceptor = AuthInterceptor(get())
        ApolloClient.Builder().serverUrl(BuildConfig.API_URL).okHttpClient(
            OkHttpClient.Builder().addInterceptor(authInterceptor).build()
        ).build()
    }
}