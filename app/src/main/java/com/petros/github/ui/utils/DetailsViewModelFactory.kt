package com.petros.github.ui.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.petros.github.data.api.ApiService
import com.petros.github.data.model.Repository
import com.petros.github.data.repositories.HomeRepository
import com.petros.github.ui.viewmodels.DetailsViewModel
import com.petros.github.ui.viewmodels.HomeViewModel

class DetailsViewModelFactory(private val repository: Repository) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when (modelClass) {
            DetailsViewModel::class.java -> DetailsViewModel(
                repository
            ) as T
            else -> throw IllegalStateException("Unsupported viewmodel")
        }
    }
}