package com.md.nasaapod.picture_list.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.md.nasaapod.MainViewModel
import com.md.nasaapod.R
import com.md.nasaapod.databinding.FragmentPictureListBinding
import com.md.nasaapod.picture_list.data.PictureListState
import com.md.nasaapod.utils.viewBinding
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

        // fetch pictures
        mainViewModel.fetchPictures()
    }

    private fun handlePictureList(pictureListState: PictureListState) {
        Log.d(TAG, "handlePictureList: $pictureListState")
    }

    companion object {
        private val TAG = PictureListFragment::class.java.simpleName
    }
}