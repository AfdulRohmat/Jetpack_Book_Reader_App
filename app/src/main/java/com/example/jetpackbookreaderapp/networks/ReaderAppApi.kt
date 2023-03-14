package com.example.jetpackbookreaderapp.networks

import com.example.jetpackbookreaderapp.features.home_fature.model.detail_book_model.DetailBookModel
import com.example.jetpackbookreaderapp.features.home_fature.model.search_book_model.SearchBookModel
import com.example.jetpackbookreaderapp.utils.Constans
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import javax.inject.Singleton

@Singleton

interface ReaderAppApi {

    // GET SEARCH BOOK RESULTS
    @Headers(
        "X-RapidAPI-Key: ${Constans.API_KEY}",
        "X-RapidAPI-Host: ${Constans.API_HOST}"
    )
    @GET("search/{searchQuery}")
    suspend fun getSearchBook(@Path("searchQuery") searchQuery: String): SearchBookModel

    // GET DETAIL BOOK
    @Headers(
        "X-RapidAPI-Key: ${Constans.API_KEY}",
        "X-RapidAPI-Host: ${Constans.API_HOST}"
    )
    @GET("book/{bookId}")
    suspend fun getDetailBook(@Path("bookId") bookId: String): DetailBookModel
}