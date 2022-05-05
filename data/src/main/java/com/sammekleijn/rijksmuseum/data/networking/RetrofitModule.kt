package com.sammekleijn.rijksmuseum.data.networking

import android.content.Context
import coil.util.CoilUtils
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.sammekleijn.rijksmuseum.data.BuildConfig
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object RetrofitModule {

    @Provides
    fun provideLoggingInterceptor(): HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    @Provides
    internal fun provideCommonOkHttpClientBuilder(
        loggingInterceptor: HttpLoggingInterceptor,
        @ApplicationContext context: Context,
    ): OkHttpClient.Builder =
        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .addInterceptor(ChuckerInterceptor.Builder(context).build())

    @Provides
    @Singleton
    fun provideOkHttpClient(
        @ApplicationContext context: Context,
        builder: OkHttpClient.Builder,
    ): OkHttpClient = builder
        .cache(CoilUtils.createDefaultCache(context))
        .build()

    @Provides
    @Singleton
    fun provideRetrofitClient(
        httpClient: OkHttpClient,
        moshi: Moshi
    ): Retrofit =
        Retrofit.Builder()
            .baseUrl(BuildConfig.RIJKSMUSEUM_BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .client(httpClient)
            .build()

    @Provides
    fun provideMoshi(): Moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()
}
