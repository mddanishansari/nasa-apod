package com.md.nasaapod.di.module

import com.fasterxml.jackson.databind.json.JsonMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object JsonModule {
    @Provides
    @Singleton
    fun provideJsonMapper(): JsonMapper {
        return JsonMapper().apply {
            registerModule(KotlinModule())
        }
    }
}
