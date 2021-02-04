package com.md.nasaapod.picture_list.adapter

import androidx.recyclerview.widget.DiffUtil
import com.md.nasaapod.picture_list.data.Picture

class PictureDiffCallback : DiffUtil.ItemCallback<Picture>() {
    override fun areItemsTheSame(oldItem: Picture, newItem: Picture): Boolean {
        return oldItem.isBookmarked == newItem.isBookmarked
    }

    override fun areContentsTheSame(oldItem: Picture, newItem: Picture): Boolean {
        return oldItem == newItem
    }
}