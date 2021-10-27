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
import com.example.aula2_iesb_lodjinha.databinding.CategoriesLayoutRvBinding
import com.example.aula2_iesb_lodjinha.models.GetBannerResponse
import com.example.aula2_iesb_lodjinha.models.GetCategoriaResponse

class CategoriasAdapter :
    ListAdapter<GetCategoriaResponse.Categoria, CategoriasAdapter.CategoriaViewHolder>(differCallback) {

    inner class CategoriaViewHolder(
        private val binding: CategoriesLayoutRvBinding
    ): RecyclerView.ViewHolder(binding.root) {

        fun bind(categoria: GetCategoriaResponse.Categoria) {
            binding.categoryIv.apply {
                load(categoria.urlImagem) {
                    crossfade(true)
                    transformations(CircleCropTransformation())
                    placeholder(R.drawable.ic_info)
                    error(R.drawable.ic_info)
                }

                setOnClickListener {
                    onItemClickListener?.invoke(categoria)
                }

            }

        }

    }

    companion object {
        private val differCallback: DiffUtil.ItemCallback<GetCategoriaResponse.Categoria> =
            object : DiffUtil.ItemCallback<GetCategoriaResponse.Categoria>(){
                override fun areItemsTheSame(
                    oldItem: GetCategoriaResponse.Categoria,
                    newItem: GetCategoriaResponse.Categoria
                ): Boolean {
                    return oldItem == newItem
                }

                override fun areContentsTheSame(
                    oldItem: GetCategoriaResponse.Categoria,
                    newItem: GetCategoriaResponse.Categoria
                ): Boolean {
                    return oldItem.id == newItem.id
                }

            }
    }

    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriaViewHolder {
        val binding = CategoriesLayoutRvBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return CategoriaViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CategoriaViewHolder, position: Int) {
        holder.bind(differ.currentList[position])
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    private var onItemClickListener: ( (GetCategoriaResponse.Categoria) -> Unit )? = null

    fun setOnItemClickListener(clickListener: (GetCategoriaResponse.Categoria) -> Unit ) {
        onItemClickListener = clickListener
    }

}