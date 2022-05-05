package com.sammekleijn.rijksmuseum.presentation.overview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.sammekleijn.rijksmuseum.domain.result.LoadResultException
import com.sammekleijn.rijksmuseum.presentation.R
import com.sammekleijn.rijksmuseum.presentation.common.asErrorResource
import com.sammekleijn.rijksmuseum.presentation.databinding.CollectionLoadingFooterBinding

internal class CollectionLoadStateAdapter(private val retry: () -> Unit) :
    LoadStateAdapter<CollectionLoadStateAdapter.CollectionLoadStateViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): CollectionLoadStateViewHolder =
        CollectionLoadStateViewHolder(
            CollectionLoadingFooterBinding.bind(
                LayoutInflater.from(parent.context).inflate(R.layout.collection_loading_footer, parent, false)
            ), retry
        )

    override fun onBindViewHolder(holder: CollectionLoadStateViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    class CollectionLoadStateViewHolder(
        private val binding: CollectionLoadingFooterBinding,
        retry: () -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.retryButton.setOnClickListener { retry() }
        }

        fun bind(loadState: LoadState) = with(binding) {
            if (loadState is LoadState.Error) {
                error.text = (loadState.error as? LoadResultException)?.errorType?.asErrorResource(root.context)
            }
            progressBar.isVisible = loadState is LoadState.Loading
            retryButton.isVisible = loadState is LoadState.Error
            error.isVisible = loadState is LoadState.Error
        }
    }
}
