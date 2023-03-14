package com.example.jetpackbookreaderapp.di

import com.example.jetpackbookreaderapp.networks.ReaderAppApi
import com.example.jetpackbookreaderapp.utils.Constans
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

    // Provide Repository API
    @Singleton
    @Provides
    fun provideBookApi(): ReaderAppApi {
        return Retrofit.Builder().baseUrl(Constans.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ReaderAppApi::class.java)
    }

    // Provide Retrofit


}