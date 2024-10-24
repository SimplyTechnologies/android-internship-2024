package com.simply.birthdayapp

import android.app.Application
import com.simply.birthdayapp.auth.authnavigation.data.di.authDataModule
import com.simply.birthdayapp.auth.authnavigation.domain.di.authDomainModule
import com.simply.birthdayapp.auth.authnavigation.presentation.di.authPresentationModule
import com.simply.birthdayapp.auth.registration.data.di.registrationDataModule
import com.simply.birthdayapp.auth.registration.domain.di.registrationDomainModule
import com.simply.birthdayapp.auth.registration.presentation.di.registrationPresentationModule
import com.simply.birthdayapp.auth.signIn.data.di.sigInDataModule
import com.simply.birthdayapp.auth.signIn.domain.di.signInDomainModule
import com.simply.birthdayapp.auth.signIn.presentation.di.signInPresentationModule
import com.simply.birthdayapp.commondata.di.commonDataModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class BirthdayApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@BirthdayApplication)

            modules(
                authDomainModule,
                authDataModule,
                authPresentationModule,
                signInDomainModule,
                sigInDataModule,
                signInPresentationModule,
                commonDataModule,
                registrationPresentationModule,
                registrationDomainModule,
                registrationDataModule
            )
        }

    }
}