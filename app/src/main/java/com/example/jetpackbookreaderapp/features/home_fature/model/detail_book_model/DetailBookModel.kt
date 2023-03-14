package com.example.jetpackbookreaderapp.features.home_fature.model.detail_book_model

data class DetailBookModel(
    val authors: List<String>,
    val book_id: Int,
    val cover: String,
    val name: String,
    val pages: Int,
    val published_date: String,
    val rating: Double,
    val synopsis: String,
    val url: String
)