package com.sammekleijn.rijksmuseum.data.startup

import android.content.Context
import androidx.startup.Initializer
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.EntryPointAccessors
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@EntryPoint
@InstallIn(SingletonComponent::class)
interface InitializerEntryPoint {

    fun inject(initializer: CoilInitializer)

    companion object {

        fun resolve(@ApplicationContext context: Context): InitializerEntryPoint {
            return EntryPointAccessors.fromApplication(context, InitializerEntryPoint::class.java)
        }
    }
}

class DependencyGraphInitializer : Initializer<Unit> {

    override fun create(context: Context) {
        InitializerEntryPoint.resolve(context)
    }

    override fun dependencies(): List<Class<out Initializer<*>>> = emptyList()
}
