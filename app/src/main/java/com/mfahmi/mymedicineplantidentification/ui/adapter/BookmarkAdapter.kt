package com.mfahmi.mymedicineplantidentification.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.RecyclerView
import com.mfahmi.mymedicineplantidentification.R
import com.mfahmi.mymedicineplantidentification.databinding.ItemsBookmarkPlantsBinding
import com.mfahmi.mymedicineplantidentification.domain.models.PlantDomain

class BookmarkAdapter : RecyclerView.Adapter<BookmarkAdapter.BookmarkAdapterViewHolder>() {

    private val listBookmark = ArrayList<PlantDomain>()

    var onItemClick: ((PlantDomain) -> Unit)? = null

    fun setData(listData: List<PlantDomain>?) {
        if (listData == null) return
        listBookmark.clear()
        listBookmark.addAll(listData)
        notifyDataSetChanged()
    }

    inner class BookmarkAdapterViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        private val binding = ItemsBookmarkPlantsBinding.bind(itemView)
        fun bind(plants: PlantDomain) {
            with(binding) {
                imgBookmark.setImageBitmap(plants.img)
                tvTitle.text = plants.title
                tvBookmarkDescription.text =
                    itemView.context.getString(
                        R.string.description_format,
                        plants.description.substring(0, 40)
                    )
                tvBookmarkDate.text = plants.date
            }
        }

        init {
            binding.root.setOnClickListener { onItemClick?.invoke(listBookmark[adapterPosition]) }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookmarkAdapterViewHolder {
        return BookmarkAdapterViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.items_bookmark_plants, parent, false)
        )
    }

    override fun onBindViewHolder(holder: BookmarkAdapterViewHolder, position: Int) {
        holder.bind(listBookmark[position])
        holder.itemView.setAnimationRecyclerView()
    }

    override fun getItemCount(): Int = listBookmark.size

    private fun View.setAnimationRecyclerView() {
        startAnimation(AnimationUtils.loadAnimation(context, R.anim.recyclerview_anim_items))
    }
}