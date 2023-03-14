package com.example.jetpackbookreaderapp.features.home_fature.repository

import com.example.jetpackbookreaderapp.data.DataOrException
import com.example.jetpackbookreaderapp.features.home_fature.model.detail_book_model.DetailBookModel
import com.example.jetpackbookreaderapp.features.home_fature.model.search_book_model.SearchBookModel
import com.example.jetpackbookreaderapp.networks.ReaderAppApi
import javax.inject.Inject

class HomeRepository @Inject constructor(private val readerAppApi: ReaderAppApi) {
    private var searchBookResult = DataOrException<SearchBookModel, Boolean, Exception>()
    private var detailBookResult = DataOrException<DetailBookModel, Boolean, Exception>()

    // GET SEARCH BOOK DATA
    suspend fun getSearchBook(searchQuery: String): DataOrException<SearchBookModel, Boolean, Exception> {
        try {
            searchBookResult.isLoading = true
            searchBookResult.data = readerAppApi.getSearchBook(searchQuery)
            if (searchBookResult.data!!.isNotEmpty()) {
                searchBookResult.isLoading = false
            }

        } catch (e: Exception) {
            searchBookResult.isLoading = false
            searchBookResult.error = e
        }
        return searchBookResult
    }

    // GET DETAIL BOOK DATA
    suspend fun getDetailBook(bookId: String): DataOrException<DetailBookModel, Boolean, Exception> {
        try {
            detailBookResult.isLoading = true
            detailBookResult.data = readerAppApi.getDetailBook(bookId)
            if (detailBookResult.data.toString().isNotEmpty()) {
                detailBookResult.isLoading = false
            }

        } catch (e: Exception) {
            detailBookResult.isLoading = false
            detailBookResult.error = e
        }
        return detailBookResult
    }
}