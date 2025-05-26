package com.jasson.conforta.local

import com.jasson.conforta.dummy.DummyData
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class LocalDataModule {

    @Provides
    @Singleton
    fun provideDummyData(): DummyData {
        return DummyData
    }
}