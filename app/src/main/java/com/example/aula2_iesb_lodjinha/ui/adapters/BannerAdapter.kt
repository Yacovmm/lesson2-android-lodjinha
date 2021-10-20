package com.example.aula2_iesb_lodjinha.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.aula2_iesb_lodjinha.databinding.BannerLayoutRvBinding
import com.example.aula2_iesb_lodjinha.models.GetBannerResponse

class BannerAdapter :
    ListAdapter<GetBannerResponse.Banner, BannerAdapter.BannerViewHolder>(differCallback) {

        inner class BannerViewHolder(
            private val binding: BannerLayoutRvBinding
        ): RecyclerView.ViewHolder(binding.root) {
            fun bind(banner: GetBannerResponse.Banner) {
                binding.image.load(banner.urlImagem)
            }
        }

    companion object {
        private val differCallback: DiffUtil.ItemCallback<GetBannerResponse.Banner> =
            object : DiffUtil.ItemCallback<GetBannerResponse.Banner>(){
                override fun areItemsTheSame(
                    oldItem: GetBannerResponse.Banner,
                    newItem: GetBannerResponse.Banner
                ): Boolean {
                    return oldItem == newItem
                }

                override fun areContentsTheSame(
                    oldItem: GetBannerResponse.Banner,
                    newItem: GetBannerResponse.Banner
                ): Boolean {
                    return oldItem.id == newItem.id
                }

            }
    }

    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BannerViewHolder {
        val binding = BannerLayoutRvBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return BannerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BannerViewHolder, position: Int) {
        holder.bind(differ.currentList[position])
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }


}