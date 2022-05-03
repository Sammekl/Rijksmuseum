package com.sammekleijn.rijksmuseum.presentation

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
internal class MainViewModel @Inject constructor(
) : ViewModel() {

    fun onDestinationChanged(destination: Int) {
        Timber.i("Switched to destination $destination")
    }
}
