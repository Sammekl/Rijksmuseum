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
import com.sammekleijn.rijksmuseum.presentation.overview.CollectionLoadStateAdapter.CollectionLoadStateViewHolder.Companion.create

internal class CollectionLoadStateAdapter(private val retry: () -> Unit) :
    LoadStateAdapter<CollectionLoadStateAdapter.CollectionLoadStateViewHolder>() {

    override fun onBindViewHolder(holder: CollectionLoadStateViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): CollectionLoadStateViewHolder {
        return create(parent, retry)
    }

    class CollectionLoadStateViewHolder(
        private val binding: CollectionLoadingFooterBinding,
        retry: () -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.retryButton.setOnClickListener { retry.invoke() }
        }

        fun bind(loadState: LoadState) = with(binding) {
            if (loadState is LoadState.Error) {
                error.text = (loadState.error as? LoadResultException)?.errorType?.asErrorResource(root.context)
            }
            progressBar.isVisible = loadState is LoadState.Loading
            retryButton.isVisible = loadState is LoadState.Error
            error.isVisible = loadState is LoadState.Error
        }

        companion object {
            fun create(parent: ViewGroup, retry: () -> Unit): CollectionLoadStateViewHolder {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.collection_loading_footer, parent, false)
                val binding = CollectionLoadingFooterBinding.bind(view)
                return CollectionLoadStateViewHolder(binding, retry)
            }
        }
    }
}
