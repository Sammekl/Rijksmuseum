package com.sammekleijn.rijksmuseum.presentation.overview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.sammekleijn.rijksmuseum.presentation.common.navigate
import com.sammekleijn.rijksmuseum.presentation.viewBindingLifecycle
import dagger.hilt.android.AndroidEntryPoint
import nl.dpgmedia.donaldduck.presentation.databinding.FragmentOverviewBinding

@AndroidEntryPoint
class OverviewFragment : Fragment() {

    private var binding: FragmentOverviewBinding by viewBindingLifecycle()

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
    }
}
