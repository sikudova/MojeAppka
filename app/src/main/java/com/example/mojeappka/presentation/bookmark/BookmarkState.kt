package com.example.mojeappka.presentation.bookmark

import com.example.mojeappka.domain.model.Article

data class BookmarkState(
    val articles: List<Article> = emptyList()
)
