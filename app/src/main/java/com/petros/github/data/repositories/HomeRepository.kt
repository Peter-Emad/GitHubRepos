package com.petros.github.data.repositories

import com.petros.github.data.api.ApiService

class HomeRepository(private val apiService: ApiService) {
    suspend fun searchRepos(query : String) = apiService.searchRepositories(query)
}