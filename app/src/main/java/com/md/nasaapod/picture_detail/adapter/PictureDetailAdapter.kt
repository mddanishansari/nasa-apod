package com.md.nasaapod.picture_detail.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.md.nasaapod.databinding.ItemPictureDetailBinding
import com.md.nasaapod.picture_list.data.Picture

class PictureDetailAdapter(private val pictureList: List<Picture>) :
    RecyclerView.Adapter<PictureDetailAdapter.PictureViewHolder>() {

    class PictureViewHolder(private val binding: ItemPictureDetailBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(picture: Picture) {
            // set title
            binding.tvTitle.text = picture.title
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