package com.md.nasaapod.picture_detail.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import com.md.nasaapod.MainViewModel
import com.md.nasaapod.R
import com.md.nasaapod.databinding.FragmentPictureDetailBinding
import com.md.nasaapod.picture_detail.adapter.PictureDetailAdapter
import com.md.nasaapod.picture_list.data.PictureListState
import com.md.nasaapod.utils.viewBinding

class PictureDetailFragment : Fragment(R.layout.fragment_picture_detail) {
    private val binding by viewBinding(FragmentPictureDetailBinding::bind)
    private val args by navArgs<PictureDetailFragmentArgs>()
    private val mainViewModel by activityViewModels<MainViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val picturePosition = args.picturePosition
        val pictureListState = mainViewModel.pictureListStateLiveData().value

        if (pictureListState is PictureListState.Success) {
            val adapter = PictureDetailAdapter(pictureListState.pictureList)
            binding.vpPictureSlider.adapter = adapter

            if (picturePosition in pictureListState.pictureList.indices) {
                binding.vpPictureSlider.setCurrentItem(picturePosition, false)
            }
        }
    }
}