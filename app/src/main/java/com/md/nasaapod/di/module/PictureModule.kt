package com.md.nasaapod.di.module

import com.fasterxml.jackson.databind.json.JsonMapper
import com.md.nasaapod.picture_list.data.PictureRepository
import com.md.nasaapod.picture_list.data.PictureRepositoryImpl
import com.md.nasaapod.utils.RawFileReader
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
object PictureModule {
    @Provides
    fun providePictureRepository(
        fileReader: RawFileReader,
        jsonMapper: JsonMapper,
    ): PictureRepository {
        return PictureRepositoryImpl(fileReader, jsonMapper)
    }
}