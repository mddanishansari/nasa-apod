package com.md.nasaapod.picture_list.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.md.nasaapod.R
import com.md.nasaapod.databinding.ItemPictureBinding
import com.md.nasaapod.picture_list.data.Picture

class PictureAdapter(
    private val pictureList: List<Picture>,
    private val onClick: (Int) -> Unit = {},
    private val onBookmarkClick: (Int, Boolean) -> Unit,
) : RecyclerView.Adapter<PictureAdapter.PictureViewHolder>() {

    class PictureViewHolder(
        private val binding: ItemPictureBinding,
        private val onClick: (Int) -> Unit,
        private val onBookmarkClick: (Int, Boolean) -> Unit,
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(picture: Picture) {
            // set thumbnail image
            binding.ivThumbnail.load(picture.thumbnailUrl) {
                placeholder(R.drawable.ic_placeholder)
                error(R.drawable.ic_error)
                crossfade(true)
            }

            // set thumbnail image
            binding.tvTitle.text = picture.title

            // set on click listener
            binding.root.setOnClickListener { onClick(adapterPosition) }

            // set on bookmark listener
            binding.ivBookmark.setOnClickListener {
                onBookmarkClick(
                    adapterPosition,
                    picture.isBookmarked
                )
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PictureViewHolder {
        val binding = ItemPictureBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PictureViewHolder(binding, onClick, onBookmarkClick)
    }

    override fun onBindViewHolder(holder: PictureViewHolder, position: Int) {
        holder.bind(pictureList[position])
    }

    override fun getItemCount() = pictureList.size
}