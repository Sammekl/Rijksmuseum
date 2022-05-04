package com.sammekleijn.rijksmuseum.presentation.overview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.sammekleijn.rijksmuseum.presentation.R
import com.sammekleijn.rijksmuseum.presentation.databinding.ItemCollectionOverviewArtworkItemBinding
import com.sammekleijn.rijksmuseum.presentation.databinding.ItemCollectionOverviewHeaderItemBinding
import com.sammekleijn.rijksmuseum.presentation.overview.OverviewListAdapter.ViewType.ARTWORK
import com.sammekleijn.rijksmuseum.presentation.overview.OverviewListAdapter.ViewType.HEADER

class OverviewListAdapter : PagingDataAdapter<CollectionViewItem, RecyclerView.ViewHolder>(CollectionItemDiffCallback()) {

    override fun getItemViewType(position: Int): Int {
        return when (val item = getItem(position)) {
            is CollectionViewItem.Header -> HEADER
            is CollectionViewItem.Artwork -> ARTWORK
            else -> throw IllegalStateException("Unknown $item at $position")
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            HEADER -> CollectionOverviewHeaderViewHolder(ItemCollectionOverviewHeaderItemBinding.inflate(inflater, parent, false))
            ARTWORK -> CollectionOverviewArtworkViewHolder(ItemCollectionOverviewArtworkItemBinding.inflate(inflater, parent, false))
            else -> throw IllegalStateException("Unsupported viewType provided")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is CollectionOverviewHeaderViewHolder -> holder.bind(getItem(position) as CollectionViewItem.Header)
            is CollectionOverviewArtworkViewHolder -> holder.bind(getItem(position) as CollectionViewItem.Artwork)
            else -> throw IllegalArgumentException("Unknown view holder ${holder.javaClass}")
        }
    }

    inner class CollectionOverviewHeaderViewHolder(
        private val binding: ItemCollectionOverviewHeaderItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: CollectionViewItem.Header) = with(binding) {
            author.text = item.author
        }
    }

    inner class CollectionOverviewArtworkViewHolder(
        private val binding: ItemCollectionOverviewArtworkItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: CollectionViewItem.Artwork) = with(binding) {
            title.text = item.title
            image.load(item.imageUrl) {
                error(R.drawable.no_art_found_placeholder)
            }
        }
    }

    object ViewType {
        const val HEADER = 1
        const val ARTWORK = 2
    }
}

private class CollectionItemDiffCallback : DiffUtil.ItemCallback<CollectionViewItem>() {
    override fun areItemsTheSame(oldItem: CollectionViewItem, newItem: CollectionViewItem): Boolean {
        return if (oldItem is CollectionViewItem.Header && newItem is CollectionViewItem.Header) {
            oldItem.author == newItem.author
        } else if (oldItem is CollectionViewItem.Artwork && newItem is CollectionViewItem.Artwork) {
            oldItem.title == newItem.title
        } else false
    }

    override fun areContentsTheSame(oldItem: CollectionViewItem, newItem: CollectionViewItem): Boolean {
        return oldItem == newItem
    }
}

