package com.sammekleijn.rijksmuseum.data.collection

import com.sammekleijn.rijksmuseum.data.collection.remote.CollectionService
import com.sammekleijn.rijksmuseum.domain.collection.CollectionRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.Reusable
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.create

@Module
@InstallIn(SingletonComponent::class)
internal abstract class CollectionModule {

    @Binds
    abstract fun bindCollectionRepository(default: DefaultCollectionRepository): CollectionRepository

    companion object {

        @Provides
        @Reusable
        internal fun provideCollectionService(retrofit: Retrofit): CollectionService = retrofit.create()
    }
}
