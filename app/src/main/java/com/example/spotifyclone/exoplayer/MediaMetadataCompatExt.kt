package com.example.spotifyclone.exoplayer

import android.support.v4.media.MediaMetadataCompat
import com.example.spotifyclone.data.entities.Song

fun MediaMetadataCompat.toSong(): Song? =
    description?.let {
        Song(
            it.mediaId ?: "",
            it.title.toString(),
            it.subtitle.toString(),
            it.mediaUri.toString(),
            it.iconUri.toString()
        )
    }