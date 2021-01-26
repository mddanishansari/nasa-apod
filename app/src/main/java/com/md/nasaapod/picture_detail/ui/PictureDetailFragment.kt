package com.md.nasaapod.picture_detail.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import com.md.nasaapod.MainViewModel
import com.md.nasaapod.R

class PictureDetailFragment : Fragment(R.layout.fragment_picture_detail) {
    private val args by navArgs<PictureDetailFragmentArgs>()
    private val mainViewModel by activityViewModels<MainViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val picturePosition = args.picturePosition
        Toast.makeText(requireContext(), "Selected position $picturePosition", Toast.LENGTH_SHORT).show()
    }
}