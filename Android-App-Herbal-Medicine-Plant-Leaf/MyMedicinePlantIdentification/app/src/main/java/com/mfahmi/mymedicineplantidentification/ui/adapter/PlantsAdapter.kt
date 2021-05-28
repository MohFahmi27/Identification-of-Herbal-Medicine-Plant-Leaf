package com.mfahmi.mymedicineplantidentification.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mfahmi.mymedicineplantidentification.data.models.Plants
import com.mfahmi.mymedicineplantidentification.databinding.ItemsHomePlantsBinding

class PlantsAdapter(private val listItem: List<Plants>) :
    RecyclerView.Adapter<PlantsAdapter.PlantsAdapterViewHolder>() {

    inner class PlantsAdapterViewHolder(private val binding: ItemsHomePlantsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(plants: Plants) {
            with(binding) {
                tvPlantItems.text = plants.name
                setGlide(itemView.context, plants.imgSrc, imgPlantItems)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlantsAdapterViewHolder {
        return PlantsAdapterViewHolder(
            ItemsHomePlantsBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: PlantsAdapterViewHolder, position: Int) {
        holder.bind(listItem[position])
    }

    override fun getItemCount(): Int = listItem.size

    private fun setGlide(context: Context, urlPath: String, imageView: ImageView) {
        Glide.with(context).load(urlPath)
            .into(imageView)
    }

}