package com.example.aula2_iesb_lodjinha.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aula2_iesb_lodjinha.api.RetrofitInstance
import com.example.aula2_iesb_lodjinha.models.GetBannerResponse
import com.example.aula2_iesb_lodjinha.repositories.LodjinhaRepository
import kotlinx.coroutines.launch

class MainViewModel(
    private val repository: LodjinhaRepository
) : ViewModel() {

    private val _bannerLiveData = MutableLiveData<List<GetBannerResponse.Banner>>()
    val bannerLiveData: LiveData<List<GetBannerResponse.Banner>> get() = _bannerLiveData

    fun getBanner() = viewModelScope.launch {
        val response = repository.getBanner()

        if (response.isSuccessful) {
            response.body()?.let {
                _bannerLiveData.postValue(it.data)
            }
        }

    }


}