package com.example.aula2_iesb_lodjinha.models

data class GetMaisVendidosResponse(
    val data: List<ProdutoResponse>
) {
    data class ProdutoResponse(
        val categoria: Categoria,
        val descricao: String,
        val id: Int,
        val nome: String,
        val precoDe: Int,
        val precoPor: Double,
        val urlImagem: String
    ) {

        data class Categoria(
            val descricao: String,
            val id: Int,
            val urlImagem: String
        )

    }
}