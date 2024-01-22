package com.example.mojeappka.domain.usecases.news

import com.example.mojeappka.data.local.NewsDao
import com.example.mojeappka.domain.model.Article
import com.example.mojeappka.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow

class SelectArticles(
    private val newsRepository: NewsRepository
) {
    operator fun invoke(): Flow<List<Article>> {
        return newsRepository.selectArticles()
    }
}