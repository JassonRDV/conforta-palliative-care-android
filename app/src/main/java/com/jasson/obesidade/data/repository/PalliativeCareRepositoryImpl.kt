package com.jasson.obesidade.data.repository

import android.content.SharedPreferences
import com.jasson.obesidade.data.local.repository.PalliativeCareLocalDataSource
import com.jasson.obesidade.data.remote.repository.PalliativeCareNetwork
import com.jasson.obesidade.domain.model.PaliativeCareItemDto
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PalliativeCareRepositoryImpl @Inject constructor(
    private val remoteDataSource: PalliativeCareNetwork,
    private val localDataSource: PalliativeCareLocalDataSource,
    private val sharedPreferences: SharedPreferences
): PalliativeCareRepository {
    
    override fun getPalliativeCareDataStream(): Flow<Result<List<PaliativeCareItemDto>>> {
        TODO("Not yet implemented")
    }
}