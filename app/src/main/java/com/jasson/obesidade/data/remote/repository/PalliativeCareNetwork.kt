package com.jasson.obesidade.data.remote.repository

import com.jasson.obesidade.domain.model.PaliativeCareResponse

interface PalliativeCareNetwork {
    suspend fun fetchPalliativeCareData(
        fileId: String,
        exportType: String = "download"
    ): Result<PaliativeCareResponse>
}