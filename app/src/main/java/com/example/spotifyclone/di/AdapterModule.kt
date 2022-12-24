package com.example.spotifyclone.di

import com.example.spotifyclone.adapters.SwipeSongAdapter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.scopes.ActivityScoped

@Module
@InstallIn(ActivityComponent::class)
object AdapterModule {
    @Provides
    @ActivityScoped
    fun providesSwipeSongAdapter() = SwipeSongAdapter()
}