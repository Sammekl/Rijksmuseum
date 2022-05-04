package com.sammekleijn.rijksmuseum.presentation.overview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.sammekleijn.rijksmuseum.presentation.R
import com.sammekleijn.rijksmuseum.presentation.databinding.ItemCollectionOverviewItemBinding

class OverviewListAdapter : PagingDataAdapter<CollectionViewItem, OverviewListAdapter.CollectionOverviewViewHolder>(CollectionItemDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CollectionOverviewViewHolder {
        return CollectionOverviewViewHolder(
            ItemCollectionOverviewItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: CollectionOverviewViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bind(it)
        }
    }

    inner class CollectionOverviewViewHolder(
        private val binding: ItemCollectionOverviewItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: CollectionViewItem) = with(binding) {
            author.text = item.author
            title.text = item.title
            image.load(item.imageUrl) {
                error(R.drawable.no_art_found_placeholder)
            }
        }
    }
}

private class CollectionItemDiffCallback : DiffUtil.ItemCallback<CollectionViewItem>() {
    override fun areItemsTheSame(oldItem: CollectionViewItem, newItem: CollectionViewItem): Boolean {
        return oldItem.title == newItem.title
    }

    override fun areContentsTheSame(oldItem: CollectionViewItem, newItem: CollectionViewItem): Boolean {
        return oldItem == newItem
    }
}

