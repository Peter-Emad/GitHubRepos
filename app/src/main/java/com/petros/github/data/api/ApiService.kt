package com.petros.github.data.api

import com.petros.github.data.model.SearchRepositoriesResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("search/repositories")
    suspend fun searchRepositories(@Query("q") query: String): SearchRepositoriesResponse
}