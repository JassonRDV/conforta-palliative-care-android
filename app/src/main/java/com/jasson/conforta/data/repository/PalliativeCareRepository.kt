package com.jasson.conforta.data.repository

import com.jasson.conforta.data.remote.PaliativeCareItemDto
import kotlinx.coroutines.flow.Flow

interface PalliativeCareRepository {
    fun getPalliativeCareData(url: String): Flow<Result<PaliativeCareItemDto>>
}