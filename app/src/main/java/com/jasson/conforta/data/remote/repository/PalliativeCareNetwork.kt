package com.jasson.conforta.data.remote.repository

import com.jasson.conforta.domain.model.PaliativeCareResponse

interface PalliativeCareNetwork {
    suspend fun fetchPalliativeCareData(
        fileId: String,
        exportType: String = "download"
    ): Result<PaliativeCareResponse>
}