package com.jasson.conforta.data.repository

import com.jasson.conforta.data.remote.ApiService
import com.jasson.conforta.data.remote.PaliativeCareItemDto
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.http.Url
import java.io.IOException // Para exceções de rede específicas

class PalliativeCareRepositoryImpl(
    private val apiService: ApiService // Injete a ApiService
) : PalliativeCareRepository {

    override fun getPalliativeCareData(url: Url): Flow<Result<PaliativeCareItemDto>> = flow {
        // Não é necessário emitir Loading aqui se usarmos kotlin.Result diretamente.
        // Se usar uma classe NetworkResult customizada, poderia emitir NetworkResult.Loading

        try {
            val response = apiService.getPalliativeCareData(url)
            emit(Result.success(response))
        } catch (e: IOException) { // Exceção comum de rede
            // Log.e("Repository", "Network error: ${e.message}", e) // Opcional: Logar o erro
            emit(Result.failure(Exception("Erro de rede: ${e.localizedMessage}", e)))
        } catch (e: retrofit2.HttpException) { // Exceção para erros HTTP (4xx, 5xx)
            // Log.e("Repository", "HTTP error ${e.code()}: ${e.message()}", e)
            emit(Result.failure(Exception("Erro HTTP ${e.code()}: ${e.message()}", e)))
        } catch (e: Exception) { // Captura genérica para outras exceções (ex: parsing JSON)
            // Log.e("Repository", "Generic error: ${e.message}", e)
            emit(Result.failure(Exception("Ocorreu um erro inesperado: ${e.localizedMessage}", e)))
        }
    }.flowOn(Dispatchers.IO) // Executa a chamada de rede na thread de IO
        .catch { e -> // Captura exceções não tratadas no flow block (menos comum aqui se já usamos try-catch)
            // Log.e("Repository", "Flow exception: ${e.message}", e)
            emit(Result.failure(Exception("Erro no fluxo de dados: ${e.localizedMessage}", e)))
        }
}

