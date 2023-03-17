package com.example.jetpackbookreaderapp.features.home_fature.view_model

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jetpackbookreaderapp.data.DataOrException
import com.example.jetpackbookreaderapp.data.Resource
import com.example.jetpackbookreaderapp.features.home_fature.model.search_book_model.SearchBookModel
import com.example.jetpackbookreaderapp.features.home_fature.repository.HomeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@SuppressLint("MutableCollectionMutableState")
@HiltViewModel
class HomeViewModel @Inject constructor(private val homeRepository: HomeRepository) : ViewModel() {
    var searchBookResult: SearchBookModel by mutableStateOf(SearchBookModel())
    var isLoading: Boolean by mutableStateOf(false)


    init {
        getSearchBook("")
    }

    fun getSearchBook(searchQuery: String) {
        viewModelScope.launch(Dispatchers.Default) {
            if (searchQuery.isEmpty()) return@launch
            isLoading = true

            try {
                when (val response = homeRepository.getSearchBook(searchQuery)) {
                    is Resource.Success -> {

                        searchBookResult = response.data!!
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

}