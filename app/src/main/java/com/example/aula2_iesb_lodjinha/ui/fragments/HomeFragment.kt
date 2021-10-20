package com.example.aula2_iesb_lodjinha.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.aula2_iesb_lodjinha.R
import com.example.aula2_iesb_lodjinha.api.RetrofitInstance
import com.example.aula2_iesb_lodjinha.databinding.FragmentHomeBinding
import com.example.aula2_iesb_lodjinha.repositories.LodjinhaRepository
import com.example.aula2_iesb_lodjinha.ui.adapters.BannerAdapter
import com.example.aula2_iesb_lodjinha.ui.viewmodels.MainViewModel
import com.example.aula2_iesb_lodjinha.ui.viewmodels.MainViewModelProvideFactory


class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding: FragmentHomeBinding get() = _binding!!

    private lateinit var bannerAdapter: BannerAdapter


    private val viewModel: MainViewModel by lazy {
        val repository = LodjinhaRepository(RetrofitInstance.apiService)
        val viewModelProviderFactory = MainViewModelProvideFactory(repository)
        ViewModelProvider(this, viewModelProviderFactory).get(MainViewModel::class.java)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupBannerRv()

        viewModel.getBanner()

    }

    // TODO SETUP OBSERVERS AND POPULATE RECYCLERVIEW

    private fun setupBannerRv() {
        bannerAdapter = BannerAdapter()
        binding.rvBanner.adapter = bannerAdapter
        binding.indicator.attachToRecyclerView(binding.rvBanner)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}