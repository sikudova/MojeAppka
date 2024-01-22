package com.example.mojeappka.domain.usecases.news

import com.example.mojeappka.data.local.NewsDao
import com.example.mojeappka.domain.model.Article

class SelectArticle(private val newsDao: NewsDao) {
    suspend operator fun invoke(url: String): Article? {
        return newsDao.getArticle(url = url)
    }
}