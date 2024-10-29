package com.simply.birthdayapp

import android.app.Application
import com.simply.birthdayapp.auth.authnavigation.data.di.authDataModule
import com.simply.birthdayapp.auth.authnavigation.domain.di.authDomainModule
import com.simply.birthdayapp.auth.authnavigation.presentation.di.authPresentationModule
import com.simply.birthdayapp.auth.registration.data.di.registrationDataModule
import com.simply.birthdayapp.auth.registration.domain.di.registrationDomainModule
import com.simply.birthdayapp.auth.registration.presentation.di.registrationPresentationModule
import com.simply.birthdayapp.auth.signIn.data.di.signInDataModule
import com.simply.birthdayapp.auth.signIn.domain.di.signInDomainModule
import com.simply.birthdayapp.auth.signIn.presentation.di.signInPresentationModule
import com.simply.birthdayapp.commondata.di.commonDataModule
import com.simply.birthdayapp.main.home.data.di.homeDataModule
import com.simply.birthdayapp.main.home.domain.di.homeDomainModule
import com.simply.birthdayapp.main.home.presentation.di.homePresentationModule
import com.simply.birthdayapp.main.shop.data.di.shopDataModule
import com.simply.birthdayapp.main.shop.domain.di.shopDomainModule
import com.simply.birthdayapp.main.shop.presentation.di.shopPresentationModule
import com.simply.birthdayapp.main.profile.profile.data.di.profileDataModule
import com.simply.birthdayapp.main.profile.profile.domain.di.profileDomainModule
import com.simply.birthdayapp.main.profile.profile.presentation.di.profilePresentationModule

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
                signInDataModule,
                signInPresentationModule,
                commonDataModule,
                registrationPresentationModule,
                registrationDomainModule,
                registrationDataModule,
                profilePresentationModule,
                profileDomainModule,
                profileDataModule,
                homePresentationModule,
                homeDataModule,
                homeDomainModule,
                shopDomainModule,
                shopPresentationModule,
                shopDataModule,

            )
        }

    }
}