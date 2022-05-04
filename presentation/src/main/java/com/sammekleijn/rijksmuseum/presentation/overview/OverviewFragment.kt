package com.sammekleijn.rijksmuseum.presentation.overview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.sammekleijn.rijksmuseum.presentation.databinding.FragmentOverviewBinding
import com.sammekleijn.rijksmuseum.presentation.viewBindingLifecycle
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import timber.log.Timber

@AndroidEntryPoint
internal class OverviewFragment : Fragment() {

    private var binding: FragmentOverviewBinding by viewBindingLifecycle()

    private val viewModel: OverviewViewModel by viewModels()

    private val adapter: OverviewListAdapter by lazy {
        OverviewListAdapter()
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

        with(binding) {
            list.adapter = adapter
        }

        with(viewModel) {
            items.observe(viewLifecycleOwner, ::onItems)

            viewLifecycleOwner.lifecycleScope.launch {
                getCollectionItems().collectLatest { items ->
                    adapter.submitData(items)
                }
            }
        }
    }

    private fun onItems(items: List<CollectionViewItem>) {
        Timber.i("items: $items")
    }
}
