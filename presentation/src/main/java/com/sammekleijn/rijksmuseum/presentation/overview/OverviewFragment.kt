package com.sammekleijn.rijksmuseum.presentation.overview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
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

    private fun onOpenArtwork(pair: Pair<CollectionViewItem.Artwork, ImageView>) {
        val action = OverviewFragmentDirections.toDetails(
            item = pair.first
        )
        pair.first.image?.url?.let { transitionName ->
            val extras = FragmentNavigatorExtras(
                pair.second to transitionName
            )
            findNavController().navigate(action, extras)
        } ?: navigate(action)
    }
}
