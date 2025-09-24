package com.jasson.conforta.data.repository

import com.jasson.conforta.domain.model.PaliativeCareItemDto
import kotlinx.coroutines.flow.Flow

interface PalliativeCareRepository {
    fun getPalliativeCareDataStream(): Flow<Result<List<PaliativeCareItemDto>>>
}