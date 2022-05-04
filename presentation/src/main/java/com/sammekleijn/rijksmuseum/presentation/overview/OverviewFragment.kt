package com.sammekleijn.rijksmuseum.presentation.overview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import com.sammekleijn.rijksmuseum.presentation.R
import com.sammekleijn.rijksmuseum.presentation.common.navigate
import com.sammekleijn.rijksmuseum.presentation.databinding.FragmentOverviewBinding
import com.sammekleijn.rijksmuseum.presentation.viewBindingLifecycle
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

@AndroidEntryPoint
internal class OverviewFragment : Fragment() {

    private var binding: FragmentOverviewBinding by viewBindingLifecycle()

    private val viewModel: OverviewViewModel by viewModels()

    private val adapter: OverviewListAdapter by lazy {
        OverviewListAdapter(viewModel::onArtworkClicked)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentOverviewBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        postponeEnterTransition()

        with(binding) {
            list.adapter = adapter.withLoadStateHeaderAndFooter(
                header = CollectionLoadStateAdapter { adapter.retry() },
                footer = CollectionLoadStateAdapter { adapter.retry() }
            )

            lifecycleScope.launch {
                adapter.loadStateFlow.collect { loadState ->
                    val isListEmpty = loadState.refresh is LoadState.NotLoading && adapter.itemCount == 0
                    val hasError = loadState.source.refresh is LoadState.Error
                    list.isVisible = !isListEmpty
                    loadingProgressbar.isVisible = loadState.source.refresh is LoadState.Loading
                    retryButton.isVisible = hasError
                    if (hasError) {
                        Toast.makeText(requireContext(), R.string.error_toast_message, Toast.LENGTH_SHORT).show()
                        retryButton.setOnClickListener { adapter.retry() }
                    }
                }
            }
        }

        with(viewModel) {
            viewLifecycleOwner.lifecycleScope.launch {
                artworks.onEach {
                    binding.list.post {
                        startPostponedEnterTransition()
                    }
                }.collectLatest {
                    adapter.submitData(it)
                }
            }

            onOpenArtWork.observe(viewLifecycleOwner, ::onOpenArtwork)
        }
    }

    private fun onOpenArtwork(pair: Pair<CollectionViewItem.ArtworkView, ImageView>) {
        val action = OverviewFragmentDirections.toDetails(
            item = pair.first
        )
        pair.first.imageUrl?.let { transitionName ->
            val extras = FragmentNavigatorExtras(
                pair.second to transitionName
            )
            findNavController().navigate(action, extras)
        } ?: navigate(action)
    }
}
