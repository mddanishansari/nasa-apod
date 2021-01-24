package com.md.nasaapod.picture_list.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.md.nasaapod.R
import com.md.nasaapod.databinding.FragmentPictureListBinding
import com.md.nasaapod.utils.viewBinding


class PictureListFragment : Fragment(R.layout.fragment_picture_list) {
    private val binding by viewBinding(FragmentPictureListBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}