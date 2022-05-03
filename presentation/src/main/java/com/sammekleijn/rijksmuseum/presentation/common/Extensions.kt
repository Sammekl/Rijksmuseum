package com.sammekleijn.rijksmuseum.presentation.common

import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.NavOptions
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.navOptions
import nl.dpgmedia.donaldduck.presentation.R

internal val <T> T.exhaustive: T
    get() = this

fun Fragment.navigate(directions: NavDirections, navOptions: NavOptions? = defaultNavOptions) =
    NavHostFragment.findNavController(this).navigate(directions, navOptions)

val defaultNavOptions = navOptions {
    anim {
        enter = R.anim.right_in
        exit = R.anim.left_out
        popEnter = R.anim.left_in
        popExit = R.anim.right_out
    }
}
