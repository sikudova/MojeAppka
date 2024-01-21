package com.example.mojeappka.di

import android.app.Application
import com.example.mojeappka.data.manager.LocalUserManagerImpl
import com.example.mojeappka.domain.manager.LocalUserManager
import com.example.mojeappka.domain.usecases.app_entry.AppEntryUseCases
import com.example.mojeappka.domain.usecases.app_entry.ReadAppEntry
import com.example.mojeappka.domain.usecases.app_entry.SaveAppEntry
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideLocalUserManager(
        application: Application
    ): LocalUserManager = LocalUserManagerImpl(application)

    @Provides
    @Singleton
    fun provideAppEntryUseCases(
        localUserManager: LocalUserManager
    ) = AppEntryUseCases(
        readAppEntry = ReadAppEntry(localUserManager),
        saveAppEntry = SaveAppEntry(localUserManager)
    )

}