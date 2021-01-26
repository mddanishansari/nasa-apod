package com.md.nasaapod.picture_detail.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.md.nasaapod.R
import com.md.nasaapod.databinding.ItemPictureDetailBinding
import com.md.nasaapod.picture_list.data.Picture

class PictureDetailAdapter(private val pictureList: List<Picture>) :
    RecyclerView.Adapter<PictureDetailAdapter.PictureViewHolder>() {

    class PictureViewHolder(private val binding: ItemPictureDetailBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(picture: Picture) {
            // set image
            binding.ivHdImage.load(picture.hdImageUrl) {
                placeholder(R.drawable.ic_placeholder)
                error(R.drawable.ic_error)
                crossfade(true)
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PictureViewHolder {
        val binding =
            ItemPictureDetailBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PictureViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PictureViewHolder, position: Int) {
        holder.bind(pictureList[position])
    }

    override fun getItemCount() = pictureList.size
}