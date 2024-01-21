package com.example.mojeappka.domain.usecases.news

import androidx.paging.PagingData
import com.example.mojeappka.domain.model.Article
import com.example.mojeappka.domain.repository.NewsRepository
import com.example.mojeappka.presentation.search.SearchEvent
import kotlinx.coroutines.flow.Flow

class SearchNews(
    private val newsRepository: NewsRepository
) {
    operator fun invoke(searchQuery: String, sources: List<String>): Flow<PagingData<Article>> {
        return newsRepository.searchNews(searchQuery = searchQuery, sources = sources)
    }
}