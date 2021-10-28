package com.example.aula2_iesb_lodjinha.api

import com.example.aula2_iesb_lodjinha.models.GetBannerResponse
import com.example.aula2_iesb_lodjinha.models.GetCategoriaResponse
import com.example.aula2_iesb_lodjinha.models.GetMaisVendidosResponse
import retrofit2.Response
import retrofit2.http.GET

interface LodjinhaService {

    @GET("banner")
    suspend fun getBanner(): Response<GetBannerResponse>

    @GET("categoria")
    suspend fun getCategoria(): Response<GetCategoriaResponse>


    @GET("produto/maisvendidos")
    suspend fun getMaisVendidos(): Response<GetMaisVendidosResponse>

}