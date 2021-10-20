package com.example.aula2_iesb_lodjinha.api

import com.example.aula2_iesb_lodjinha.models.GetBannerResponse
import retrofit2.Response
import retrofit2.http.GET

interface LodjinhaService {

    @GET("banner")
    suspend fun getBanner(): Response<GetBannerResponse>

}