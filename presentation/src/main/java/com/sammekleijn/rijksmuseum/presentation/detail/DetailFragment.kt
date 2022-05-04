package com.sammekleijn.rijksmuseum.presentation.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.transition.TransitionInflater
import coil.load
import com.sammekleijn.rijksmuseum.presentation.R
import com.sammekleijn.rijksmuseum.presentation.databinding.FragmentDetailBinding
import com.sammekleijn.rijksmuseum.presentation.overview.CollectionViewItem
import com.sammekleijn.rijksmuseum.presentation.viewBindingLifecycle
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
internal class DetailFragment : Fragment() {

    private var binding: FragmentDetailBinding by viewBindingLifecycle()
    private val viewModel: DetailViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(viewModel) {
            artwork.observe(viewLifecycleOwner, ::onArtwork)
        }
    }

    private fun onArtwork(artwork: CollectionViewItem.Artwork) = with(binding) {
        sharedElementEnterTransition = TransitionInflater.from(requireContext())
            .inflateTransition(android.R.transition.move)
        header.transitionName = artwork.image?.url
        header.isVisible = true
        header.load(artwork.image?.url) {
            if (artwork.image != null) size(artwork.image.width, artwork.image.height)
            error(R.drawable.no_art_found_illustration)
            fallback(R.drawable.no_art_found_illustration)
        }
        toolbarLayout.title = artwork.title
    }

}

