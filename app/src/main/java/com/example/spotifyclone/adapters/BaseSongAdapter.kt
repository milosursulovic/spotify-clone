@file:Suppress("DEPRECATION")

package com.example.spotifyclone.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.spotifyclone.data.entities.Song
import com.example.spotifyclone.databinding.ListItemBinding

abstract class BaseSongAdapter(
    private val layoutId: Int
) : RecyclerView.Adapter<BaseSongAdapter.SongViewHolder>() {

    protected val diffCallback = object : DiffUtil.ItemCallback<Song>() {
        override fun areItemsTheSame(oldItem: Song, newItem: Song): Boolean =
            oldItem.mediaId == newItem.mediaId

        override fun areContentsTheSame(oldItem: Song, newItem: Song): Boolean =
            oldItem.hashCode() == newItem.hashCode()
    }

    protected abstract val differ: AsyncListDiffer<Song>

    var songs: List<Song>
        get() = differ.currentList
        set(value) = differ.submitList(value)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SongViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(layoutId, parent, false)
        return SongViewHolder(ListItemBinding.bind(view))
    }

    override fun getItemCount(): Int = differ.currentList.size

    protected var onItemClickListener: ((Song) -> Unit)? = null

    fun setItemClickListener(onItemClickListener: (Song) -> Unit) {
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