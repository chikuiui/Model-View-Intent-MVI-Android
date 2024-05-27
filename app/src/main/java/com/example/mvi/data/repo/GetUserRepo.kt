package com.example.mvi.data.repo

import com.example.mvi.data.api.ApiService

class GetUserRepo(private val apiService: ApiService) {

    suspend fun getPosts()  = apiService.getPosts()
}