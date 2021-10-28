package com.example.aula2_iesb_lodjinha.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import com.example.aula2_iesb_lodjinha.R
import com.example.aula2_iesb_lodjinha.databinding.MaisvendidosLayoutRvBinding
import com.example.aula2_iesb_lodjinha.models.GetMaisVendidosResponse

class ProductsAdapter :
    ListAdapter<GetMaisVendidosResponse.ProdutoResponse, ProductsAdapter.ProdutoViewHolder>(differCallback) {

        inner class ProdutoViewHolder(
            private val binding: MaisvendidosLayoutRvBinding
        ): RecyclerView.ViewHolder(binding.root) {

            fun bind(model: GetMaisVendidosResponse.ProdutoResponse) {
                binding.apply {
                    productIv.load(model.urlImagem) {
                        crossfade(true)
                        placeholder(R.drawable.ic_info)
                        error(R.drawable.ic_info)

                        listener(
                            onSuccess = { request, data ->
                                println("Successo lojinha")
                            },
                            onError = { request, throwable ->
                                println("Error lojinha")
                                println(throwable.localizedMessage)
                                println(request)
                            }
                        )

                    }

                    descricaoProduto.text = model.descricao

                    dePriceProduct.text = "R$ ${model.precoDe}"
                    porPriceProduct.text = "R$ ${model.precoPor}"

                    root.setOnClickListener {
                        onItemClickListener?.invoke(model)
                    }

                }

            }

        }

    companion object {
        private val differCallback: DiffUtil.ItemCallback<GetMaisVendidosResponse.ProdutoResponse> =
            object : DiffUtil.ItemCallback<GetMaisVendidosResponse.ProdutoResponse>(){
                override fun areItemsTheSame(
                    oldItem: GetMaisVendidosResponse.ProdutoResponse,
                    newItem: GetMaisVendidosResponse.ProdutoResponse
                ): Boolean {
                    return oldItem == newItem
                }

                override fun areContentsTheSame(
                    oldItem: GetMaisVendidosResponse.ProdutoResponse,
                    newItem: GetMaisVendidosResponse.ProdutoResponse
                ): Boolean {
                    return oldItem.id == newItem.id
                }

            }
    }

    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProdutoViewHolder {
        val binding = MaisvendidosLayoutRvBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ProdutoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProdutoViewHolder, position: Int) {
        holder.bind(differ.currentList[position])
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    private var onItemClickListener: ( (GetMaisVendidosResponse.ProdutoResponse) -> Unit )? = null

    fun setOnItemClickListener(clickListener: (GetMaisVendidosResponse.ProdutoResponse) -> Unit ) {
        onItemClickListener = clickListener
    }

}