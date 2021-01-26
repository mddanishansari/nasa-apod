package com.md.nasaapod.picture_detail.ui

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import com.md.nasaapod.view_model.MainViewModel
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

        // get the picture data
        val picturePosition = args.picturePosition
        val pictureListState = mainViewModel.pictureListStateLiveData().value

        val isPictureListStateSuccess = pictureListState is PictureListState.Success
        val isSelectedPicturePresent = pictureListState is PictureListState.Success &&
                picturePosition in pictureListState.pictureList.indices

        // change visibility of data/ error layout based on the state
        binding.vpPictureSlider.isVisible = isPictureListStateSuccess && isSelectedPicturePresent
        binding.layoutError.isVisible = !binding.vpPictureSlider.isVisible

        if (pictureListState is PictureListState.Success) {
            // set adapter in ViewPager
            val adapter = PictureDetailAdapter(pictureListState.pictureList)
            binding.vpPictureSlider.adapter = adapter

            // scroll to current selected picture slide
            if (isSelectedPicturePresent) {
                binding.vpPictureSlider.setCurrentItem(picturePosition, false)
            }
        }
    }
}