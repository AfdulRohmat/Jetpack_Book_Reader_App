package com.example.jetpackbookreaderapp.networks

import com.example.jetpackbookreaderapp.features.home_fature.model.detail_book_model.DetailBookModel
import com.example.jetpackbookreaderapp.features.home_fature.model.search_book_model.SearchBookModel
import retrofit2.http.GET
import retrofit2.http.Path
import javax.inject.Singleton

@Singleton

interface ReaderAppApi {

    // GET SEARCH BOOK RESULTS
    @GET("search/{searchQuery}")
    suspend fun getSearchBook(@Path("searchQuery") searchQuery : String): SearchBookModel

    // GET DETAIL BOOK
    @GET("book/{bookId}")
    suspend fun getDetailBook(@Path("bookId") bookId : String) : DetailBookModel
}