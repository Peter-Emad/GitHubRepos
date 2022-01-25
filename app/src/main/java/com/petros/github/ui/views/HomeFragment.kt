package com.petros.github.ui.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.petros.github.data.api.RetrofitBuilder
import com.petros.github.databinding.FragmentHomeBinding
import com.petros.github.ui.utils.HomeViewModelFactory
import com.petros.github.ui.viewmodels.HomeViewModel

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        setViewModel()
        val binding = FragmentHomeBinding.inflate(inflater, container, false).also {
            it.viewModel = homeViewModel
        }
        return binding.root
    }

    private fun setViewModel() {
        homeViewModel =
            ViewModelProvider(
                this,
                HomeViewModelFactory(apiService = RetrofitBuilder.apiService,
                    onNavigateToDetailsScreen = { repository ->
                        findNavController().navigate(
                            HomeFragmentDirections.actionHomeFragmentToDetailsFragment(
                                repository
                            )
                        )
                    })
            ).get(HomeViewModel::class.java)
    }
}