package com.example.mojeappka.domain.usecases.news

import com.example.mojeappka.data.local.NewsDao
import com.example.mojeappka.domain.model.Article
import com.example.mojeappka.domain.repository.NewsRepository

class SelectArticle(private val newsRepository: NewsRepository) {
    suspend operator fun invoke(url: String): Article? {
        return newsRepository.selectArticle(url = url)
    }
}