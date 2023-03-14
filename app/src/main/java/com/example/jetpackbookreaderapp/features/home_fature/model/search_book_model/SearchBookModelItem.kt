package com.example.jetpackbookreaderapp.features.home_fature.model.search_book_model

data class SearchBookModelItem(
    val authors: List<String>,
    val book_id: Int,
    val cover: String,
    val created_editions: Int,
    val name: String,
    val rating: Double,
    val url: String,
    val year: Int
)