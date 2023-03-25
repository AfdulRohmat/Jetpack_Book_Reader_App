package com.example.jetpackbookreaderapp.features.home_fature.view_model

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jetpackbookreaderapp.data.DataOrException
import com.example.jetpackbookreaderapp.data.Resource
import com.example.jetpackbookreaderapp.features.detail_book_feature.model.BookModel
import com.example.jetpackbookreaderapp.features.home_fature.model.search_book_model.Item
import com.example.jetpackbookreaderapp.features.home_fature.model.search_book_model.SearchBookModel
import com.example.jetpackbookreaderapp.features.home_fature.repository.FirestoreBookRepository
import com.example.jetpackbookreaderapp.features.home_fature.repository.HomeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@SuppressLint("MutableCollectionMutableState")
@HiltViewModel
class HomeViewModel @Inject constructor(
    private val homeRepository: HomeRepository,
    private val firestoreBookRepository: FirestoreBookRepository
) : ViewModel() {
    var searchBookResult: List<Item> by mutableStateOf(listOf())
    var booksFromFirestoreData: MutableState<DataOrException<List<BookModel>, Boolean, Exception>> =
        mutableStateOf(DataOrException(data = listOf(), false, Exception("")))
    var isLoading: Boolean by mutableStateOf(false)



    init {
        getSearchBook("")
//        getAllBooksFromFirestore()
    }

    // SEARCH BOOK
    fun getSearchBook(searchQuery: String) {
        viewModelScope.launch(Dispatchers.Default) {
            if (searchQuery.isEmpty()) return@launch
            isLoading = true

            try {
                when (val response = homeRepository.getSearchBook(searchQuery)) {
                    is Resource.Success -> {
                        searchBookResult = response.data!!.items
                        if (searchBookResult.isNotEmpty()) isLoading = false
                    }
                    is Resource.Error -> {
                        isLoading = false
                        Log.e("Network", "searchBooks: Failed getting books")
                    }
                    else -> {
                        isLoading = false
                    }
                }
            } catch (e: Exception) {
                isLoading = false
                Log.d("Network", "searchBooks: ${e.message.toString()}")
            }

        }
    }

    // GET BOOK FROM FIRESTORE
     fun getAllBooksFromFirestore() {
        viewModelScope.launch {
            booksFromFirestoreData.value.isLoading = true
            booksFromFirestoreData.value = firestoreBookRepository.getAllBooksFromFirestore()
            if (!booksFromFirestoreData.value.data.isNullOrEmpty()) booksFromFirestoreData.value.isLoading =
                false
        }
        Log.d("firestore data", booksFromFirestoreData.value.data?.toList().toString())
    }
}