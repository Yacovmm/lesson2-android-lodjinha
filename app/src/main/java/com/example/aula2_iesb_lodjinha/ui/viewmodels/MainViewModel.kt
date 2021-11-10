package com.example.aula2_iesb_lodjinha.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aula2_iesb_lodjinha.api.RetrofitInstance
import com.example.aula2_iesb_lodjinha.models.GetBannerResponse
import com.example.aula2_iesb_lodjinha.models.GetCategoriaResponse
import com.example.aula2_iesb_lodjinha.models.GetMaisVendidosResponse
import com.example.aula2_iesb_lodjinha.repositories.LodjinhaRepository
import com.example.aula2_iesb_lodjinha.ui.viewstates.HomeData
import com.example.aula2_iesb_lodjinha.ui.viewstates.HomeViewState
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import retrofit2.Response
import kotlin.system.measureTimeMillis

class MainViewModel(
    private val repository: LodjinhaRepository
) : ViewModel() {

    private val _homeDataLiveData = MutableLiveData<HomeViewState>()
    val homeDataLiveData: LiveData<HomeViewState> get() = _homeDataLiveData

    fun getMainHomeData() = viewModelScope.launch {
        _homeDataLiveData.postValue(
                HomeViewState(
                    loading = true
                )
        )

        val time1 = measureTimeMillis {
            val bannerResponse = async { repository.getBanner() }
            val categoriesResponse = async { repository.getCategoria() }
            val maisVendidosResponse = async { repository.getMaisVendidos() }


            val homeState = handleHomeResponse(
                bannerResponse.await(),
                categoriesResponse.await(),
                maisVendidosResponse.await()
            )

            _homeDataLiveData.postValue(homeState)
        }
        // Before 1296 ms
        // Before 892 ms
        println("Tempo do requests é? $time1 ms")
    }

    private fun handleHomeResponse(
        bannerResponse: Response<GetBannerResponse>,
        categoriesResponse: Response<GetCategoriaResponse>,
        maisVendidosResponse: Response<GetMaisVendidosResponse>
    ): HomeViewState {
        if (bannerResponse.isSuccessful and
                categoriesResponse.isSuccessful and
                maisVendidosResponse.isSuccessful) {
            return HomeViewState(
                loading = false,
                error = false,
                data = HomeData(
                    bannerData = bannerResponse.body()?.data,
                    categoriesData = categoriesResponse.body()?.data,
                    maisVendidosData = maisVendidosResponse.body()?.data
                )
            )

        }
        return HomeViewState(
            loading = false,
            error = true,
            data = null
        )
    }

    private val _categoriesLiveData = MutableLiveData<List<GetCategoriaResponse.Categoria>>()
    val categoriesLiveData: LiveData<List<GetCategoriaResponse.Categoria>> get() = _categoriesLiveData

    fun getCategories() = viewModelScope.launch {
        val response = repository.getCategoria()

        if (response.isSuccessful) {
            response.body()?.let {
                _categoriesLiveData.postValue(it.data)
            }
        }
    }

    private val _maisVendidosLiveData = MutableLiveData<List<GetMaisVendidosResponse.ProdutoResponse>>()
    val maisVendidosLiveData: LiveData<List<GetMaisVendidosResponse.ProdutoResponse>> get() = _maisVendidosLiveData

    fun getMaisVendidos() = viewModelScope.launch {
        val response = repository.getMaisVendidos()

        if (response.isSuccessful) {
            response.body()?.let {
                _maisVendidosLiveData.postValue(it.data)
            }
        }

    }


}