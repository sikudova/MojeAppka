package com.example.mojeappka.di

import android.app.Application
import androidx.room.Room
import com.example.mojeappka.data.local.NewsDao
import com.example.mojeappka.data.local.NewsDatabase
import com.example.mojeappka.data.local.NewsTypeConverter
import com.example.mojeappka.data.manager.LocalUserManagerImpl
import com.example.mojeappka.data.remote.NewsApi
import com.example.mojeappka.data.repository.NewsRepositoryImpl
import com.example.mojeappka.domain.manager.LocalUserManager
import com.example.mojeappka.domain.repository.NewsRepository
import com.example.mojeappka.domain.usecases.app_entry.AppEntryUseCases
import com.example.mojeappka.domain.usecases.app_entry.ReadAppEntry
import com.example.mojeappka.domain.usecases.app_entry.SaveAppEntry
import com.example.mojeappka.domain.usecases.news.DeleteArticle
import com.example.mojeappka.domain.usecases.news.GetNews
import com.example.mojeappka.domain.usecases.news.NewsUseCases
import com.example.mojeappka.domain.usecases.news.SearchNews
import com.example.mojeappka.domain.usecases.news.SelectArticle
import com.example.mojeappka.domain.usecases.news.SelectArticles
import com.example.mojeappka.domain.usecases.news.UpsertArticle
import com.example.mojeappka.util.Constants.BASE_URL
import com.example.mojeappka.util.Constants.NEWS_DATABASE_NAME
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
        newsApi: NewsApi,
        newsDao: NewsDao
    ): NewsRepository {
        return NewsRepositoryImpl(newsApi, newsDao)
    }

    @Provides
    @Singleton
    fun provideNewsUseCases(
        newsRepository: NewsRepository,
    ): NewsUseCases {
        return NewsUseCases(
            getNews = GetNews(newsRepository),
            searchNews = SearchNews(newsRepository),
            upsertArticle = UpsertArticle(newsRepository),
            deleteArticle = DeleteArticle(newsRepository),
            selectArticle = SelectArticle(newsRepository),
            selectArticles = SelectArticles(newsRepository)
        )
    }

    @Provides
    @Singleton
    fun provideNewsDatabase(
        application: Application
    ): NewsDatabase {
        return Room.databaseBuilder(
            context = application,
            klass = NewsDatabase::class.java,
            name = NEWS_DATABASE_NAME
        ).addTypeConverter(NewsTypeConverter())
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideNewsDao(
        newsDatabase: NewsDatabase
    ): NewsDao = newsDatabase.newsDao

}