package com.md.nasaapod.picture_list.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.md.nasaapod.R
import com.md.nasaapod.databinding.ItemPictureBinding
import com.md.nasaapod.picture_list.data.Picture

class PictureAdapter(
    private val onClick: (Int) -> Unit = {},
    private val onBookmarkClick: (Int, Boolean) -> Unit,
) : ListAdapter<Picture, PictureAdapter.PictureViewHolder>(PictureDiffCallback()) {

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

            // set bookmark icon
            val bookmarkIcon = if (picture.isBookmarked) {
                R.drawable.ic_bookmark_enable
            } else {
                R.drawable.ic_bookmark_disable
            }
            binding.ivBookmark.load(bookmarkIcon)

            // set on click listener
            binding.root.setOnClickListener { onClick(adapterPosition) }

            // set on bookmark listener
            binding.ivBookmark.setOnClickListener {
                onBookmarkClick(
                    adapterPosition,
                    !picture.isBookmarked
                )
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PictureViewHolder {
        val binding = ItemPictureBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PictureViewHolder(binding, onClick, onBookmarkClick)
    }

    override fun onBindViewHolder(holder: PictureViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

}