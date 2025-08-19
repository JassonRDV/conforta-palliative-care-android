package com.jasson.obesidade.data.remote.repository

import com.jasson.obesidade.data.remote.ApiService
import com.jasson.obesidade.domain.model.PaliativeCareResponse
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PalliativeCareNetworkImpl @Inject constructor(
    private val apiService: ApiService
) : PalliativeCareNetwork {

    override suspend fun fetchPalliativeCareData(
        fileId: String,
        exportType: String
    ): Result<PaliativeCareResponse> {
        return try {
            val response = apiService.getJsonDataFromGoogleDrive(
                fileId = fileId,
                exportType = exportType
            )

            if (response.isSuccessful) {
                response.body()?.let { data ->
                    Result.success(data)
                }
                    ?: Result.failure(Exception("Resposta do servidor está vazia."))
            } else {
                Result.failure(Exception(
                    "Erro da API: ${response.code()} - ${response.message()}")
                )
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}

