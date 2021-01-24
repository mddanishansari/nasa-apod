package com.md.nasaapod

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.md.nasaapod.databinding.ActivityMainBinding
import com.md.nasaapod.utils.viewBinding


class MainActivity : AppCompatActivity() {
    private val binding by viewBinding(ActivityMainBinding::inflate)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}