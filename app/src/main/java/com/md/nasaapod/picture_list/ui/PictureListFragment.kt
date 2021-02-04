package com.md.nasaapod.picture_list.ui

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.md.nasaapod.R
import com.md.nasaapod.databinding.FragmentPictureListBinding
import com.md.nasaapod.picture_list.adapter.PictureAdapter
import com.md.nasaapod.picture_list.data.PictureListState
import com.md.nasaapod.utils.viewBinding
import com.md.nasaapod.view_model.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PictureListFragment : Fragment(R.layout.fragment_picture_list) {
    private val binding by viewBinding(FragmentPictureListBinding::bind)

    private val mainViewModel by activityViewModels<MainViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // observe picture list state
        mainViewModel.pictureListStateLiveData()
            .observe(viewLifecycleOwner) { handlePictureList(it) }

        // fetch pictures only if necessary
        val pictureListState = mainViewModel.pictureListStateLiveData().value
        if (pictureListState is PictureListState.Success && pictureListState.pictureList.isNotEmpty()) {
            handlePictureList(pictureListState)
        } else {
            mainViewModel.fetchPictures()
        }

        // set recyclerview adapter
        val pictureAdapter = PictureAdapter(::onPictureClick, ::onBookmarkClick)
        binding.rvPicture.adapter = pictureAdapter

        // user should be able to fetch pictures if something goes wrong
        binding.btntryAgain.setOnClickListener { mainViewModel.fetchPictures() }
    }

    private fun handlePictureList(pictureListState: PictureListState) {

        // change visibility of views according to different states
        binding.layoutLoading.isVisible = pictureListState is PictureListState.Loading
        binding.layoutError.isVisible = pictureListState is PictureListState.Failed
        binding.layoutData.isVisible = pictureListState is PictureListState.Success

        if (pictureListState is PictureListState.Success) {
            val pictureAdapter = binding.rvPicture.adapter as PictureAdapter
            pictureAdapter.submitList(pictureListState.pictureList)
        }
    }

    private fun onPictureClick(position: Int) {
        // navigate to detail screen
        val action =
            PictureListFragmentDirections.actionPictureListFragmentToPictureDetailFragment(
                position
            )
        findNavController().navigate(action)
    }

    private fun onBookmarkClick(position: Int, isBookmarked: Boolean) {
        mainViewModel.bookmark(position, isBookmarked)
    }

    companion object {
        private val TAG = PictureListFragment::class.java.simpleName
    }
}