package com.jasson.obesidade.data.repository

import com.jasson.obesidade.domain.model.PaliativeCareItemDto
import kotlinx.coroutines.flow.Flow

interface PalliativeCareRepository {
    fun getPalliativeCareDataStream(): Flow<Result<List<PaliativeCareItemDto>>>
}