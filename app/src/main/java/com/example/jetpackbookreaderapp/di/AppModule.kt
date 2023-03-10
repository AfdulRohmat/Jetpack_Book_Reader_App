package com.example.jetpackbookreaderapp.di

import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    // PROVIDE ANYTHING LIKE ROOM DB, DAO, AND RETROFIT
}