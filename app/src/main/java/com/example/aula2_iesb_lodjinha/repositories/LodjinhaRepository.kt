package com.example.aula2_iesb_lodjinha.repositories

import com.example.aula2_iesb_lodjinha.api.LodjinhaService
import com.example.aula2_iesb_lodjinha.api.RetrofitInstance
import com.example.aula2_iesb_lodjinha.api.SafeApiCall
import com.example.aula2_iesb_lodjinha.models.GetBannerResponse
import com.example.aula2_iesb_lodjinha.models.GetCategoriaResponse
import retrofit2.Response

class LodjinhaRepository(
    private val service: LodjinhaService
) {

    suspend fun getBanner(): Response<GetBannerResponse> {
        return SafeApiCall.safeNetworkRequest {
            service.getBanner()
        } ?: Response.success(null)
    }

    suspend fun getCategoria(): Response<GetCategoriaResponse> {
        return SafeApiCall.safeNetworkRequest {
            service.getCategoria()
        } ?: Response.success(null)
    }
}