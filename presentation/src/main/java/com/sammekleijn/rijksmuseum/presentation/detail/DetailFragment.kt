package com.sammekleijn.rijksmuseum.presentation.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.transition.TransitionInflater
import coil.load
import coil.size.OriginalSize
import com.sammekleijn.rijksmuseum.domain.common.StringResource
import com.sammekleijn.rijksmuseum.presentation.R
import com.sammekleijn.rijksmuseum.presentation.common.viewBindingLifecycle
import com.sammekleijn.rijksmuseum.presentation.databinding.FragmentDetailBinding
import com.sammekleijn.rijksmuseum.presentation.overview.CollectionViewItem
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
            productionPlaces.observe(viewLifecycleOwner, ::onProductionPlaces)
        }
    }

    private fun onArtwork(artwork: CollectionViewItem.ArtworkView) = with(binding) {
        sharedElementEnterTransition = TransitionInflater.from(requireContext()).inflateTransition(android.R.transition.move)
        header.transitionName = artwork.image?.url
        header.load(artwork.image?.url) {
            size(OriginalSize)
            error(R.drawable.no_art_found_illustration)
            placeholder(R.drawable.no_art_found_illustration)
            fallback(R.drawable.no_art_found_illustration)
        }
        toolbarLayout.title = artwork.title
        binding.content.longTitle.text = artwork.content.longTitle
        binding.content.objectNumber.text = artwork.content.objectNumber
    }

    private fun onProductionPlaces(resource: StringResource) {
        binding.content.productionPlaces.text = resource.get(requireContext())
    }
}

