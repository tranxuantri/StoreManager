package com.example.myapplication.di

import com.example.myapplication.data.network.ProductApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Singleton
    @Provides
    fun providesApiService():ProductApiService {
        return Retrofit.Builder()
            .baseUrl("https://storemanager-af1bb-default-rtdb.asia-southeast1.firebasedatabase.app")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ProductApiService::class.java)
    }
}