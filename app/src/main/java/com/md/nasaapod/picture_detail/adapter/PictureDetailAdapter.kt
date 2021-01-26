package com.md.nasaapod.picture_detail.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.md.nasaapod.R
import com.md.nasaapod.databinding.ItemPictureDetailBinding
import com.md.nasaapod.picture_list.data.Picture
import java.text.SimpleDateFormat
import java.util.*

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

            // set title
            binding.tvTitle.text = picture.title

            // set date
            binding.tvDate.text =
                SimpleDateFormat("dd MMM yyyy", Locale.getDefault()).format(picture.date)

            // set copyright
            if (picture.copyright != null) {
                binding.tvCopyright.text =
                    binding.root.context.getString(R.string.copyright, picture.copyright)
            }

            // set explanation
            binding.tvExplanation.text = picture.explanation
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