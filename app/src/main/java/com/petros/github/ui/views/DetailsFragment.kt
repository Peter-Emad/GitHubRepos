package com.petros.github.ui.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.petros.github.R
import com.petros.github.data.api.RetrofitBuilder
import com.petros.github.databinding.FragmentDetailsBinding
import com.petros.github.databinding.FragmentHomeBinding
import com.petros.github.ui.utils.DetailsViewModelFactory
import com.petros.github.ui.utils.HomeViewModelFactory
import com.petros.github.ui.viewmodels.DetailsViewModel
import com.petros.github.ui.viewmodels.HomeViewModel

class DetailsFragment : Fragment() {

    private lateinit var detailsViewModel: DetailsViewModel

    private val args: DetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        setViewModel()
        val binding = FragmentDetailsBinding.inflate(inflater, container, false).also {
            it.viewModel = detailsViewModel
        }
        return binding.root
    }

    private fun setViewModel() {
        detailsViewModel =
            ViewModelProvider(
                this,
                DetailsViewModelFactory(args.repository)
            ).get(DetailsViewModel::class.java)
    }
}