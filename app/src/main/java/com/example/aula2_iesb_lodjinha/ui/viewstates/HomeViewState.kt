package com.example.aula2_iesb_lodjinha.ui.viewstates

import com.example.aula2_iesb_lodjinha.models.GetBannerResponse
import com.example.aula2_iesb_lodjinha.models.GetCategoriaResponse
import com.example.aula2_iesb_lodjinha.models.GetMaisVendidosResponse

data class HomeViewState(
    var loading: Boolean = false,
    var error: Boolean = false,
    var data: HomeData? = null
)

data class HomeData(
    val bannerData: List<GetBannerResponse.Banner>?,
    val categoriesData: List<GetCategoriaResponse.Categoria>?,
    val maisVendidosData: List<GetMaisVendidosResponse.ProdutoResponse>?
)
