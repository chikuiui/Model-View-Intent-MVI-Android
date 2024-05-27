package com.example.mvi.data.api

import com.example.mvi.data.model.FakeDTO
import retrofit2.http.GET

interface ApiService {

    @GET("posts")
    suspend fun getPosts() : List<FakeDTO>

}