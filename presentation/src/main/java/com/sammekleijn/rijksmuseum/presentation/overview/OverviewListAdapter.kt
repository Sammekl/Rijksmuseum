package com.sammekleijn.rijksmuseum.presentation.overview

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.size.OriginalSize
import com.sammekleijn.rijksmuseum.presentation.R
import com.sammekleijn.rijksmuseum.presentation.databinding.ItemCollectionOverviewArtworkItemBinding
import com.sammekleijn.rijksmuseum.presentation.databinding.ItemCollectionOverviewHeaderItemBinding
import com.sammekleijn.rijksmuseum.presentation.overview.OverviewListAdapter.ViewType.ARTWORK
import com.sammekleijn.rijksmuseum.presentation.overview.OverviewListAdapter.ViewType.HEADER

class OverviewListAdapter(
    private val onItemClicked: (item: CollectionViewItem.ArtworkView, ImageView) -> Unit
) : PagingDataAdapter<CollectionViewItem, RecyclerView.ViewHolder>(CollectionItemDiffCallback()) {

    override fun getItemViewType(position: Int): Int {
        return when (val item = getItem(position)) {
            is CollectionViewItem.HeaderView -> HEADER
            is CollectionViewItem.ArtworkView -> ARTWORK
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
            is CollectionOverviewHeaderViewHolder -> holder.bind(getItem(position) as CollectionViewItem.HeaderView)
            is CollectionOverviewArtworkViewHolder -> holder.bind(getItem(position) as CollectionViewItem.ArtworkView)
            else -> throw IllegalArgumentException("Unknown view holder ${holder.javaClass}")
        }
    }

    inner class CollectionOverviewHeaderViewHolder(
        private val binding: ItemCollectionOverviewHeaderItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: CollectionViewItem.HeaderView) = with(binding) {
            author.text = item.author
        }
    }

    inner class CollectionOverviewArtworkViewHolder(
        private val binding: ItemCollectionOverviewArtworkItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: CollectionViewItem.ArtworkView) = with(binding) {
            title.text = item.title
            image.transitionName = item.imageUrl
            image.load(item.imageUrl) {
                size(OriginalSize)
                error(R.drawable.no_art_found_illustration)
                fallback(R.drawable.no_art_found_illustration)
            }
            root.setOnClickListener { onItemClicked(item, image) }
        }
    }

    object ViewType {
        const val HEADER = 1
        const val ARTWORK = 2
    }
}

private class CollectionItemDiffCallback : DiffUtil.ItemCallback<CollectionViewItem>() {
    override fun areItemsTheSame(oldItem: CollectionViewItem, newItem: CollectionViewItem): Boolean {
        return if (oldItem is CollectionViewItem.HeaderView && newItem is CollectionViewItem.HeaderView) {
            oldItem.author == newItem.author
        } else if (oldItem is CollectionViewItem.ArtworkView && newItem is CollectionViewItem.ArtworkView) {
            oldItem.title == newItem.title
        } else false
    }

    override fun areContentsTheSame(oldItem: CollectionViewItem, newItem: CollectionViewItem): Boolean {
        return oldItem == newItem
    }
}

