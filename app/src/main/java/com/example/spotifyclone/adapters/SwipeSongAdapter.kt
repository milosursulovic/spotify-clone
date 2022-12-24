package com.example.spotifyclone.adapters

import androidx.recyclerview.widget.AsyncListDiffer
import com.example.spotifyclone.R

class SwipeSongAdapter : BaseSongAdapter(R.layout.list_item) {
    override val differ = AsyncListDiffer(this, diffCallback)

    override fun onBindViewHolder(holder: SongViewHolder, position: Int) {
        val song = songs[position]
        holder.binding.apply {
            val text = "${song.title} - ${song.subtitle}"
            tvPrimary.text = text
        }
    }
}