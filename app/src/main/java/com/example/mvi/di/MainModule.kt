package com.example.mvi.di

import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import com.example.mvi.data.api.ApiService
import com.example.mvi.data.repo.GetUserRepo
import dagger.Module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

@Module
@InstallIn(SingletonComponent::class)
object MainModule {

    @Singleton
    @Provides
    fun provideApiService() : ApiService {
         return Retrofit.Builder().baseUrl("https://jsonplaceholder.typicode.com/")
             .addConverterFactory(GsonConverterFactory.create())
             .build()
             .create(ApiService::class.java)
    }


    @Provides
    fun provideUserRepo(apiService: ApiService) : GetUserRepo = GetUserRepo(apiService)
}