package com.example.jetpackbookreaderapp.features.home_fature.view_model

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jetpackbookreaderapp.data.DataOrException
import com.example.jetpackbookreaderapp.features.home_fature.model.search_book_model.SearchBookModel
import com.example.jetpackbookreaderapp.features.home_fature.repository.HomeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val homeRepository: HomeRepository) : ViewModel() {
    val searchBookResultsData: MutableState<DataOrException<SearchBookModel, Boolean, Exception>> =
        mutableStateOf(DataOrException(null, false, Exception("")))

    init {
        getSearchBook("")

    }

    fun getSearchBook(searchQuery: String) {
        viewModelScope.launch {
            if (searchQuery.isEmpty()) {
                return@launch
            }

            searchBookResultsData.value.isLoading = true
            searchBookResultsData.value = homeRepository.getSearchBook(searchQuery)
            Log.d("search: ", searchBookResultsData.value.data.toString())
            if (searchBookResultsData.value.data.toString().isNotEmpty()) {
                searchBookResultsData.value.isLoading = false
            }

        }
    }

}