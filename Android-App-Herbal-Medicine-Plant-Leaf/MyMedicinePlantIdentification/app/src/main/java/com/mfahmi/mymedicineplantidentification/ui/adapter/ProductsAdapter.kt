package com.mfahmi.mymedicineplantidentification.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mfahmi.mymedicineplantidentification.databinding.ItemsProductsPlantsBinding
import com.mfahmi.mymedicineplantidentification.domain.models.Plants

class ProductsAdapter(private val listItem: List<Plants>) :
    RecyclerView.Adapter<ProductsAdapter.ProductsAdapterViewHolder>() {

    inner class ProductsAdapterViewHolder(private val binding: ItemsProductsPlantsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(plants: Plants) {
            with(binding) {
                tvPlantItems.text = plants.name
                setGlide(itemView.context, plants.imgSrc, imgPlantItems)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductsAdapter.ProductsAdapterViewHolder {
        return ProductsAdapterViewHolder(
            ItemsProductsPlantsBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ProductsAdapter.ProductsAdapterViewHolder, position: Int) {
        holder.bind(listItem[position])
    }

    override fun getItemCount(): Int = listItem.size

    private fun setGlide(context: Context, urlPath: String, imageView: ImageView) {
        Glide.with(context).load(urlPath)
            .into(imageView)
    }
}