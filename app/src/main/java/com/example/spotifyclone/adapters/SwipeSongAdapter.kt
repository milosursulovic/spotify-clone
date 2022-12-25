package com.example.spotifyclone.adapters

import androidx.recyclerview.widget.AsyncListDiffer
import com.example.spotifyclone.R
import com.example.spotifyclone.databinding.SwipeItemBinding

class SwipeSongAdapter : BaseSongAdapter(R.layout.swipe_item) {
    override val differ = AsyncListDiffer(this, diffCallback)

    override fun onBindViewHolder(holder: SongViewHolder, position: Int) {
        val song = songs[position]
        (holder.binding as SwipeItemBinding).apply {
            val text = "${song.title} - ${song.subtitle}"
            tvPrimary.text = text
        }
    }
}