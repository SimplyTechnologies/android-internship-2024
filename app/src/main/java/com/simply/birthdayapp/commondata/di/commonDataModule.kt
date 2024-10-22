package com.simply.birthdayapp.commondata.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "isSignedIn")

val commonDataModule = module {
    single { provideDataStore(context = androidApplication()) }
}

private fun provideDataStore(context: Context) = context.dataStore