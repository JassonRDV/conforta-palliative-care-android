package com.jasson.obesidade.di

import android.content.Context
import androidx.room.Room
import com.jasson.obesidade.data.local.AppDatabase
import com.jasson.obesidade.data.local.PalliativeCareDao
import com.jasson.obesidade.data.local.repository.PalliativeCareLocalDataSource
import com.jasson.obesidade.data.local.repository.PalliativeCareLocalDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContenxt: Context): AppDatabase {
        return Room.databaseBuilder(
            appContenxt,
            AppDatabase::class.java,
            "conforta_database"
        ).build()
    }

    @Provides
    @Singleton
    fun providePalliativeCareDao(appDatabase: AppDatabase) : PalliativeCareDao {
        return appDatabase.palliativeCareDao()

    }

    @Provides
    @Singleton
    fun providePalliativeCareLocalDataSource(
        palliativeCareDao: PalliativeCareDao
    ) : PalliativeCareLocalDataSource {
        return PalliativeCareLocalDataSourceImpl(palliativeCareDao)
    }
}