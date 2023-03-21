package com.example.jetpackbookreaderapp.networks

import com.example.jetpackbookreaderapp.features.home_fature.model.search_book_model.Item
import com.example.jetpackbookreaderapp.features.home_fature.model.search_book_model.SearchBookModel
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import javax.inject.Singleton

@Singleton

interface ReaderAppApi {

    // GET SEARCH BOOK RESULTS
    @GET("volumes")
    suspend fun getSearchBook(@Query("q") q : String, @Query("key") key:String): SearchBookModel

    // GET DETAIL BOOK
    @GET("volumes/{book_Id}")
    suspend fun getDetailBook(@Path("book_Id") book_Id: String): Item
}