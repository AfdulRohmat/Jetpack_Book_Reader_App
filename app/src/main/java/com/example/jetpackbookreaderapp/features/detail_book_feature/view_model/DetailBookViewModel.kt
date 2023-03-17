package com.example.jetpackbookreaderapp.features.detail_book_feature.view_model

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.jetpackbookreaderapp.data.Resource
import com.example.jetpackbookreaderapp.features.home_fature.model.detail_book_model.DetailBookModel
import com.example.jetpackbookreaderapp.features.home_fature.repository.HomeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailBookViewModel @Inject constructor(private val homeRepository: HomeRepository) :
    ViewModel() {

    suspend fun getDetailBook(bookId: String): Resource<DetailBookModel> {
        val response = homeRepository.getDetailBook(bookId)
        Log.d("response view model: ", response.data.toString())
        return homeRepository.getDetailBook(bookId)
    }

}