package com.example.jetpackbookreaderapp.di

import com.example.jetpackbookreaderapp.features.home_fature.repository.FirestoreBookRepository
import com.example.jetpackbookreaderapp.features.home_fature.repository.HomeRepository
import com.example.jetpackbookreaderapp.networks.ReaderAppApi
import com.example.jetpackbookreaderapp.utils.Constans
import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    // PROVIDE ANYTHING LIKE ROOM DB, DAO, AND RETROFIT

    // Provide Retrofit
    @Singleton
    @Provides
    fun provideBookApi(): ReaderAppApi {
        return Retrofit.Builder().baseUrl(Constans.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ReaderAppApi::class.java)
    }

    // Provide Repository API
    // provide home repository
    @Singleton
    @Provides
    fun provideHomeRepository(readerAppApi: ReaderAppApi) = HomeRepository(readerAppApi)

    // Provide Firestore Repository
    @Singleton
    @Provides
    fun provideFirestoreBookRepository() =
        FirestoreBookRepository(bookQuery = FirebaseFirestore.getInstance().collection("books"))


}