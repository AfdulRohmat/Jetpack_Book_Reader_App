package com.example.jetpackbookreaderapp.features.detail_book_feature.view_model

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.jetpackbookreaderapp.data.Resource
import com.example.jetpackbookreaderapp.features.home_fature.model.search_book_model.Item
import com.example.jetpackbookreaderapp.features.home_fature.repository.HomeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailBookViewModel @Inject constructor(private val homeRepository: HomeRepository) :
    ViewModel() {

    suspend fun getDetailBook(bookId: String): Resource<Item> {
        val response = homeRepository.getDetailBook(bookId)
        Log.d("response view model: ", response.data.toString())
        return homeRepository.getDetailBook(bookId)
    }

}