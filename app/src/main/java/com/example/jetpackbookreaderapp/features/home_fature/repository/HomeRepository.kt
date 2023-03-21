package com.example.jetpackbookreaderapp.features.home_fature.repository

import com.example.jetpackbookreaderapp.data.Resource
import com.example.jetpackbookreaderapp.features.home_fature.model.search_book_model.Item
import com.example.jetpackbookreaderapp.features.home_fature.model.search_book_model.SearchBookModel
import com.example.jetpackbookreaderapp.networks.ReaderAppApi
import com.example.jetpackbookreaderapp.utils.Constans
import javax.inject.Inject

class HomeRepository @Inject constructor(private val readerAppApi: ReaderAppApi) {

    // GET SEARCH BOOK DATA
    suspend fun getSearchBook(searchQuery: String): Resource<SearchBookModel> {
        return try {
            Resource.Loading(data = true)
            val searchBookResult = readerAppApi.getSearchBook(searchQuery, Constans.API_KEY)
            if (searchBookResult.items.isNotEmpty()) Resource.Loading(data = false)
            Resource.Success(data = searchBookResult)

        } catch (e: Exception) {
            Resource.Loading(data = false)
            Resource.Error(message = e.message.toString())
        }
    }

    // GET DETAIL BOOK BY ID
    suspend fun getDetailBook(bookId: String): Resource<Item> {
        val response = try {
            Resource.Loading(data = true)
            readerAppApi.getDetailBook(bookId)

        } catch (e: Exception) {
            Resource.Loading(data = false)
            return Resource.Error(message = "An error occurred ${e.message.toString()}")
        }

        Resource.Loading(data = false)
        return Resource.Success(data = response)
    }
}