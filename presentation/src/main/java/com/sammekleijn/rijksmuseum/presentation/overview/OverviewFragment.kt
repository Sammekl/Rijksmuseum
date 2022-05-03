package com.sammekleijn.rijksmuseum.presentation.overview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.sammekleijn.rijksmuseum.domain.collection.CollectionItem
import com.sammekleijn.rijksmuseum.presentation.databinding.FragmentOverviewBinding
import com.sammekleijn.rijksmuseum.presentation.viewBindingLifecycle
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
internal class OverviewFragment : Fragment() {

    private var binding: FragmentOverviewBinding by viewBindingLifecycle()

    private val viewModel: OverviewViewModel by viewModels()

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

        with(binding) {
            toDetails.setOnClickListener {
                navigate(OverviewFragmentDirections.toDetails())
            }
        }

        with(viewModel) {
            items.observe(viewLifecycleOwner, ::onItems)
        }
    }

    private fun onItems(items: List<CollectionItem>) {
        Timber.i("items: $items")
    }
}
