package com.md.nasaapod.picture_list.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.md.nasaapod.R
import com.md.nasaapod.databinding.ItemPictureBinding
import com.md.nasaapod.picture_list.data.Picture

class PictureAdapter(private val pictureList: List<Picture>) :
    RecyclerView.Adapter<PictureAdapter.PictureViewHolder>() {

    class PictureViewHolder(private val binding: ItemPictureBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(picture: Picture) {
            binding.ivThumbnail.load(picture.thumbnailUrl) {
                placeholder(R.drawable.ic_thumbnail_placeholder)
                error(R.drawable.ic_thumbnail_error)
                crossfade(true)
            }
            binding.tvTitle.text = picture.title
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PictureViewHolder {
        val binding = ItemPictureBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PictureViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PictureViewHolder, position: Int) {
        holder.bind(pictureList[position])
    }

    override fun getItemCount() = pictureList.size
}