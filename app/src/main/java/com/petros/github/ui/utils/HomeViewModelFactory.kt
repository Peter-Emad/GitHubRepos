package com.petros.github.ui.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.petros.github.data.api.ApiService
import com.petros.github.data.model.Repository
import com.petros.github.data.repositories.HomeRepository
import com.petros.github.ui.viewmodels.HomeViewModel

class HomeViewModelFactory(
    private val apiService: ApiService,
    private val onNavigateToDetailsScreen: (Repository) -> Unit
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when (modelClass) {
            HomeViewModel::class.java -> HomeViewModel(
                homeRepository = HomeRepository(apiService = apiService),
                onNavigateToDetailsScreen = onNavigateToDetailsScreen
            ) as T
            else -> throw IllegalStateException("Unsupported viewmodel")
        }
    }
}