package com.jasson.conforta.data.remote.repository

import com.jasson.conforta.data.remote.PaliativeCareResponse

interface PalliativeCareRepository {
    suspend fun getPalliativeCareData(
        fileId: String,
        exportType: String = "download"
    ): Result<PaliativeCareResponse>
}