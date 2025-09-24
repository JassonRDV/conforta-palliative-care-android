package com.jasson.conforta.data.usecase

import android.content.SharedPreferences
import android.util.Log
import androidx.core.content.edit
import com.jasson.conforta.data.local.repository.PalliativeCareLocalDataSource
import com.jasson.conforta.data.local.toDto
import com.jasson.conforta.data.local.toEntity
import com.jasson.conforta.domain.model.CuidadoPaliativoAgrupado
import com.jasson.conforta.domain.model.DetalheSubcategoria
import com.jasson.conforta.domain.model.PaliativeCareItemDto
import com.jasson.conforta.data.remote.repository.PalliativeCareNetwork
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetPalliativeCareDataUseCase @Inject constructor(
    private val remoteDataSource: PalliativeCareNetwork,
    private val localDataSource: PalliativeCareLocalDataSource,
    private val sharedPreferences: SharedPreferences
) {

    private val LAST_SUCCESSFUL_FETCH_TIMESTAMP_KEY = "last_successful_fetch_timestamp"
    private val UPDATE_INTERVAL_MILLISECONDS = TimeUnit.DAYS.toMillis(1)

    suspend operator fun invoke(
        fileId: String, exportType: String = "download"
    ): Result<List<CuidadoPaliativoAgrupado>> {
        val currentTime = System.currentTimeMillis()
        val lastFetchTime = sharedPreferences.getLong(LAST_SUCCESSFUL_FETCH_TIMESTAMP_KEY, 0L)

        if (
            localDataSource.hasCachedData() &&
            (currentTime - lastFetchTime < UPDATE_INTERVAL_MILLISECONDS)
        ) {
            Log.d(
                "UseCase",
                "Dados recentes encontrados no cache. Carregando e agrupando do cache."
            )
            val cachedEntities = localDataSource.getPalliativeCareItems()
            val dtos = cachedEntities.map { it.toDto() }
            val agrupados = performGrouping(dtos)
            return Result.success(agrupados)
        }

        Log.d("UseCase", "Cache desatualizado ou inexistente. Buscando da rede...")
        val networkResult =
            remoteDataSource.fetchPalliativeCareData(fileId = fileId, exportType = exportType)

        return networkResult.fold(
            onSuccess = { networkResponse ->
                Log.d("UseCase", "Sucesso na busca da rede. Salvando no cache e agrupando.")
                localDataSource.clearAllPalliativeCareItems()
                localDataSource.savePalliativeCareItems(networkResponse.itens.map { it.toEntity() })


                sharedPreferences.edit {
                    putLong(LAST_SUCCESSFUL_FETCH_TIMESTAMP_KEY, currentTime)
                }
                Log.d("UseCase", "Timestamp da última busca atualizado.")


                val agrupados = performGrouping(networkResponse.itens)
                Result.success(agrupados)
            },
            onFailure = { networkException ->
                Log.w(
                    "UseCase",
                    "Falha na busca da rede: ${networkException.message}. Tentando usar cache como fallback."
                )
                if (localDataSource.hasCachedData()) {
                    Log.d(
                        "UseCase",
                        "Rede falhou, mas encontrou dados no cache (fallback). Agrupando do cache."
                    )
                    val cachedEntities = localDataSource.getPalliativeCareItems()
                    val dtos = cachedEntities.map { it.toDto() }
                    val agrupados = performGrouping(dtos)
                    Result.success(agrupados)
                } else {
                    Log.e(
                        "UseCase",
                        "Falha na rede e cache vazio. Erro: ${networkException.message}"
                    )
                    Result.failure(networkException)
                }
            }
        )
    }

    private fun performGrouping(itemsDto: List<PaliativeCareItemDto>): List<CuidadoPaliativoAgrupado> {
        return itemsDto
            .groupBy { it.categoria }
            .map { (nomeCategoria, itensDaMesmaCategoria) ->
                CuidadoPaliativoAgrupado(
                    nomeCategoriaPrincipal = nomeCategoria,
                    subcategorias = itensDaMesmaCategoria.map { itemDto ->
                        DetalheSubcategoria(
                            nomeSubcategoria = itemDto.subcategoria,
                            descricao = itemDto.descricao,
                        )
                    }
                )
            }
    }
}