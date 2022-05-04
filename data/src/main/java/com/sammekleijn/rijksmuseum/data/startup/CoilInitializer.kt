package com.sammekleijn.rijksmuseum.data.startup

import android.content.Context
import android.graphics.Bitmap
import androidx.startup.Initializer
import coil.Coil
import coil.ImageLoader
import okhttp3.OkHttpClient
import javax.inject.Inject

class CoilInitializer : Initializer<Unit> {

    @Inject
    lateinit var okHttpClient: OkHttpClient

    override fun create(context: Context) {
        InitializerEntryPoint.resolve(context).inject(this)

        Coil.setImageLoader(
            ImageLoader.Builder(context)
                .crossfade(true)
                .okHttpClient(okHttpClient)
                .bitmapConfig(Bitmap.Config.ARGB_8888)
                .build()
        )
    }

    override fun dependencies(): List<Class<out Initializer<*>>> = listOf(
        DependencyGraphInitializer::class.java,
    )
}
