package com.example.mojeappka.data.remote.dto

import com.example.mojeappka.domain.model.Article

data class NewsResponse(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)