package com.example.aula2_iesb_lodjinha.models

data class GetBannerResponse(
    val data: List<Banner>
) {
    data class Banner(
        val id: Int = 0,
        val linkUrl: String = "",
        val urlImagem: String = ""
    )
}