package com.example.mojeappka.domain.usecases.news

import com.example.mojeappka.data.local.NewsDao
import com.example.mojeappka.domain.model.Article
import kotlinx.coroutines.flow.Flow

class SelectArticles(
    private val newsDao: NewsDao
) {
    operator fun invoke(): Flow<List<Article>> {
        return newsDao.getArticles()
    }
}