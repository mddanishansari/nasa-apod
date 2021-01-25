package com.md.nasaapod

import android.app.Application
import coil.ImageLoader
import coil.ImageLoaderFactory
import coil.decode.SvgDecoder
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App : Application(), ImageLoaderFactory {

    override fun newImageLoader() = ImageLoader.Builder(applicationContext)
        .componentRegistry {
            add(SvgDecoder(applicationContext))
        }
        .build()
}