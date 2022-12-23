package com.example.spotifyclone.di

import android.app.Application
import com.example.spotifyclone.exoplayer.MusicServiceConnection
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ConnectionModule {
    @Provides
    @Singleton
    fun providesServiceConnection(app: Application) =
        MusicServiceConnection(app)
}