package com.example.mojeappka.di

import android.app.Application
import com.example.mojeappka.data.manager.LocalUserManagerImpl
import com.example.mojeappka.data.remote.NewsApi
import com.example.mojeappka.data.repository.NewsRepositoryImpl
import com.example.mojeappka.domain.manager.LocalUserManager
import com.example.mojeappka.domain.repository.NewsRepository
import com.example.mojeappka.domain.usecases.app_entry.AppEntryUseCases
import com.example.mojeappka.domain.usecases.app_entry.ReadAppEntry
import com.example.mojeappka.domain.usecases.app_entry.SaveAppEntry
import com.example.mojeappka.domain.usecases.news.GetNews
import com.example.mojeappka.domain.usecases.news.NewsUseCases
import com.example.mojeappka.util.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
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

    @Provides
    @Singleton
    fun provideApiInstance(): NewsApi {
        return Retrofit
            .Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NewsApi::class.java)
    }

    @Provides
    @Singleton
    fun provideNewsRepository(
        newsApi: NewsApi
    ): NewsRepository {
        return NewsRepositoryImpl(newsApi)
    }

    @Provides
    @Singleton
    fun provideNewsUseCases(
        newsRepository: NewsRepository
    ): NewsUseCases {
        return NewsUseCases(
            getNews = GetNews(newsRepository),
//            searchNews = SearchNews(newsRepository)
        )
    }

}