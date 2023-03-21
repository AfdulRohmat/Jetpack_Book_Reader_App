package com.example.jetpackbookreaderapp.features.home_fature.model.search_book_model

data class SearchBookModel(
    val items: List<Item>,
    val kind: String,
    val totalItems: Int
)