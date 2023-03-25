package com.example.jetpackbookreaderapp.features.home_fature.repository

import com.example.jetpackbookreaderapp.data.DataOrException
import com.example.jetpackbookreaderapp.features.detail_book_feature.model.BookModel
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.Query
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class FirestoreBookRepository @Inject constructor(
    private val bookQuery: Query
) {
    suspend fun getAllBooksFromFirestore(): DataOrException<List<BookModel>, Boolean, Exception> {
        val dataOrException = DataOrException<List<BookModel>, Boolean, Exception>()

        try {
            dataOrException.isLoading = true
            dataOrException.data = bookQuery.get().await().documents.map { documentSnapshot ->
                documentSnapshot.toObject(BookModel::class.java)!!
            }

            if (!dataOrException.data.isNullOrEmpty()) dataOrException.isLoading = false

        } catch (e: FirebaseFirestoreException) {
            dataOrException.error = e
            dataOrException.isLoading = false
        }

        return dataOrException
    }
}