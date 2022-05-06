package com.sammekleijn.rijksmuseum.domain.common

import android.content.Context
import androidx.annotation.StringRes

sealed class StringResource {

    abstract fun get(context: Context): String?

    data class Id(@StringRes val resId: Int) : StringResource() {
        override fun get(context: Context): String = context.getString(resId)
    }

    data class Value(val value: String?) : StringResource() {
        override fun get(context: Context): String? = value
    }
}
