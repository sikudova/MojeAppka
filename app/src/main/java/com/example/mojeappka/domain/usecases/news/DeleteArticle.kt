package com.example.mojeappka.domain.usecases.news

import com.example.mojeappka.data.local.NewsDao
import com.example.mojeappka.domain.model.Article

class DeleteArticle(private val newsDao: NewsDao) {
    suspend operator fun invoke(article: Article) {
        newsDao.delete(article = article)
    }

}