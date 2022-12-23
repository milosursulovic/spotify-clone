@file:Suppress("DEPRECATION")

package com.example.spotifyclone.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.example.spotifyclone.data.entities.Song
import com.example.spotifyclone.databinding.ListItemBinding
import javax.inject.Inject

class SongAdapter @Inject constructor(
    private val glide: RequestManager
) : RecyclerView.Adapter<SongAdapter.SongViewHolder>() {

    private val diffCallback = object : DiffUtil.ItemCallback<Song>() {
        override fun areItemsTheSame(oldItem: Song, newItem: Song): Boolean =
            oldItem.mediaId == newItem.mediaId

        override fun areContentsTheSame(oldItem: Song, newItem: Song): Boolean =
            oldItem.hashCode() == newItem.hashCode()
    }

    private val differ = AsyncListDiffer(this, diffCallback)

    var songs: List<Song>
        get() = differ.currentList
        set(value) = differ.submitList(value)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SongViewHolder =
        SongViewHolder(ListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: SongViewHolder, position: Int) {
        val song = songs[position]
        holder.binding.apply {
            tvPrimary.text = song.title
            tvSecondary.text = song.subtitle
            glide.load(song.imageUrl).into(ivItemImage)
        }
    }

    override fun getItemCount(): Int = differ.currentList.size

    private var onItemClickListener: ((Song) -> Unit)? = null

    fun setOnItemClickListener(onItemClickListener: (Song) -> Unit) {
        this.onItemClickListener = onItemClickListener
    }

    inner class SongViewHolder(val binding: ListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            binding.root.setOnClickListener {
                onItemClickListener?.let {
                    it(songs[adapterPosition])
                }
            }
        }
    }
}