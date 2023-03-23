package com.example.jetpackbookreaderapp.features.detail_book_feature.view_model

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.jetpackbookreaderapp.data.Resource
import com.example.jetpackbookreaderapp.features.detail_book_feature.model.BookModel
import com.example.jetpackbookreaderapp.features.home_fature.model.search_book_model.Item
import com.example.jetpackbookreaderapp.features.home_fature.repository.HomeRepository
import com.google.firebase.firestore.FirebaseFirestore
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailBookViewModel @Inject constructor(private val homeRepository: HomeRepository) :
    ViewModel() {
    private val db = FirebaseFirestore.getInstance()
    private val dbCollection = db.collection("books")

    private val _loading = mutableStateOf(false)
    val loading: MutableState<Boolean> = _loading


    suspend fun getDetailBook(bookId: String): Resource<Item> {
        val response = homeRepository.getDetailBook(bookId)
        Log.d("response view model: ", response.data.toString())
        return homeRepository.getDetailBook(bookId)
    }

    fun addBookToFirebase(book: BookModel, context: Context, navController: NavController) =
        viewModelScope.launch {
            _loading.value = true
            if (book.toString().isNotEmpty()) {
                dbCollection.add(book).addOnSuccessListener { documentReference ->
                    val docId = documentReference.id
                    dbCollection.document(docId)
                        .update(hashMapOf("id" to docId) as Map<String, Any>)
                        .addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                Toast.makeText(
                                    context, "Book added successfully",
                                    Toast.LENGTH_SHORT
                                ).show()
                                navController.popBackStack()
                                _loading.value = false
                            }
                        }.addOnFailureListener {
                            Toast.makeText(
                                context, "Failed to adding book, error : $it",
                                Toast.LENGTH_SHORT
                            ).show()
                            _loading.value = false
                        }
                }
            }
        }


}