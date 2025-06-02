package com.jasson.conforta.data.remote.repository

import com.jasson.conforta.data.remote.ApiService
import com.jasson.conforta.data.remote.PaliativeCareResponse
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PalliativeCareRepositoryImpl @Inject constructor(
    private val apiService: ApiService // Sua ApiService do Retrofit
) : PalliativeCareRepository {

    override suspend fun getPalliativeCareData(
        fileId: String,
        exportType: String
    ): Result<PaliativeCareResponse> {
        return try {
            val response = apiService.getPalliativeCareData(
                fileId = fileId,
                exportType = exportType
            )

            if (response.isSuccessful) {
                response.body()?.let { data ->
                    Result.success(data)
                }
                    ?: Result.failure(Exception("Resposta do servidor está vazia."))
            } else {
                Result.failure(Exception("Erro da API: ${response.code()} - ${response.message()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}

