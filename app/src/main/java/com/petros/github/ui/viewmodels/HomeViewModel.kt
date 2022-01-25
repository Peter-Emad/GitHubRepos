package com.petros.github.ui.viewmodels

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.petros.github.data.model.SearchRepositoriesResponse
import com.petros.github.data.repositories.HomeRepository
import com.petros.github.ui.adapters.RepositoriesAdapter
import kotlinx.coroutines.launch


const val CONTENT_STATE_VIEW_FLIPPER_ID = -1
const val LOADING_STATE_VIEW_FLIPPER_ID = 0
const val EMPTY_STATE_VIEW_FLIPPER_ID = 1
const val ERROR_STATE_VIEW_FLIPPER_ID = 2

class HomeViewModel(private val homeRepository: HomeRepository) : ViewModel() {
    private lateinit var repositoriesAdapter: RepositoriesAdapter

    val showContent = ObservableField(false)
    val rvAdapter: ObservableField<RepositoriesAdapter> = ObservableField()
    val screenState = ObservableField(ScreenState.EMPTY_STATE)
    val resultsCount: ObservableField<Int?> = ObservableField()

    init {
        setRecyclerAdapter()
    }

    val onNewQueryPushed: (String?) -> Unit = { newQuery ->
        if (isValidQuery(newQuery))
            searchRepos(newQuery!!)
    }

    private fun isValidQuery(newQuery: String?): Boolean {
        return newQuery?.let { it.length > 2 } ?: false
    }

    private fun searchRepos(query: String) {
        viewModelScope.launch {
            updateScreenState(ScreenState.LOADING_STATE)
            try {
                repositoriesAdapter.clearRepositories()
                handleSearchResponse(homeRepository.searchRepos(query))
            } catch (e: Exception) {
                updateScreenState(ScreenState.ERROR_STATE)
            }
        }
    }

    private fun updateScreenState(updatedScreenState: ScreenState) {
        screenState.set(updatedScreenState)
        showContent.set(updatedScreenState == ScreenState.CONTENT_STATE)
    }

    private fun handleSearchResponse(searchRepos: SearchRepositoriesResponse) {
        if (searchRepos.total_count > 0) {
            updateScreenState(ScreenState.CONTENT_STATE)
            resultsCount.set(searchRepos.total_count)
            repositoriesAdapter.addRepositories(searchRepos.items)
        } else
            screenState.set(ScreenState.EMPTY_STATE)
    }

    private fun setRecyclerAdapter() {
        repositoriesAdapter =
            RepositoriesAdapter(repositories = arrayListOf(), onRepositoryClick = { repo ->

            })
        rvAdapter.set(repositoriesAdapter)
    }
}


enum class ScreenState(val viewId: Int) {
    CONTENT_STATE(CONTENT_STATE_VIEW_FLIPPER_ID),
    LOADING_STATE(LOADING_STATE_VIEW_FLIPPER_ID),
    EMPTY_STATE(EMPTY_STATE_VIEW_FLIPPER_ID),
    ERROR_STATE(ERROR_STATE_VIEW_FLIPPER_ID)
}