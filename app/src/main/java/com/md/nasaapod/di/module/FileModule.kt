package com.md.nasaapod.di.module

import android.content.Context
import com.md.nasaapod.utils.RawFileReader
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object FileModule {
    @Provides
    @Singleton
    fun provideRawFileReader(@ApplicationContext context: Context): RawFileReader {
        return RawFileReader(context)
    }
}