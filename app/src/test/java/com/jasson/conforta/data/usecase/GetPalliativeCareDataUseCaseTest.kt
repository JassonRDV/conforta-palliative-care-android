package com.jasson.conforta.data.usecase

import android.content.SharedPreferences
import android.util.Log
import com.jasson.conforta.data.local.PaliativeCareItemEntity
import com.jasson.conforta.data.local.repository.PalliativeCareLocalDataSource
import com.jasson.conforta.data.remote.PaliativeCareItemDto
import com.jasson.conforta.data.remote.PaliativeCareResponse
import com.jasson.conforta.data.remote.repository.PalliativeCareNetwork
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.mockkStatic
import io.mockk.runs
import io.mockk.slot
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import java.util.concurrent.TimeUnit

class GetPalliativeCareDataUseCaseTest {

    // 1. Declarar Mocks para as dependências
    private lateinit var remoteDataSource: PalliativeCareNetwork
    private lateinit var localDataSource: PalliativeCareLocalDataSource
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var sharedPreferencesEditor: SharedPreferences.Editor

    // 2. Declarar a classe que estamos testando (System Under Test - SUT)
    private lateinit var useCase: GetPalliativeCareDataUseCase

    // 3. Métado de Setup
    @Before
    fun setUp() {
        // Inicializa os mocks
        remoteDataSource = mockk()
        localDataSource = mockk()
        sharedPreferences = mockk()
        sharedPreferencesEditor = mockk()

        // Configura o comportamento do mock de SharedPreferences
        every { sharedPreferences.getLong(any(), any()) } returns 0L
        every { sharedPreferences.edit() } returns sharedPreferencesEditor
        every { sharedPreferencesEditor.putLong(any(), any()) } returns sharedPreferencesEditor
        every { sharedPreferencesEditor.apply() } just runs

        // Inicializa a classe a ser testada com os mocks
        useCase = GetPalliativeCareDataUseCase(
            remoteDataSource,
            localDataSource,
            sharedPreferences
        )

        // Mock do Log.d para evitar erros no ambiente de teste local
        mockkStatic(Log::class)
        every { Log.d(any(), any()) } returns 0
    }

    // 4. Novo Teste
    @Test
    fun `dado que cache esta vazio, quando busco na rede sucede, deve salvar salvar no cache e retornar sucesso`() =
        runTest {
            // Given (Dado) - A preparação do cenário
            val dummyFileId = "123"
            val mockDto = PaliativeCareItemDto(
                "Categoria Teste", "Sub Teste", "Desc",
                listOf("Prof"), "Alta", "Ref"
            )
            val mockResponse = PaliativeCareResponse(listOf(mockDto))

            // Configura o comportamento dos mocks para este teste específico
            coEvery { localDataSource.hasCachedData() } returns false
            coEvery { remoteDataSource.fetchPalliativeCareData(dummyFileId) } returns
                    Result.success(mockResponse)
            coEvery { localDataSource.clearAllPalliativeCareItems() } just runs
            coEvery { localDataSource.savePalliativeCareItems(any()) } just runs

            // When (Quando) - A execução da ação que queremos testar
            val result = useCase(fileId = dummyFileId)

            // Then (Então) - A verificação do resultado e comportamentos
            assertTrue(result.isSuccess)
            val groupedData = result.getOrNull()
            assertEquals(1, groupedData?.size)
            assertEquals("Categoria Teste", groupedData?.first()?.nomeCategoriaPrincipal)

            // Verifica se os métados corretos foram chamados nos mocks (verificação de comportamento)
            coVerify(exactly = 1) { localDataSource.clearAllPalliativeCareItems() }
            coVerify(exactly = 1) { localDataSource.savePalliativeCareItems(any()) }

            val slot = slot<String>()
            val longSlot = slot<Long>()
            coVerify(exactly = 1) {
                sharedPreferencesEditor.putLong(capture(slot), capture(longSlot))
            }
            assertEquals("last_successful_fetch_timestamp", slot.captured)
        }

    @Test
    fun `dado que cache esta recente, quando chamado, deve carregar do cache rede`() = runTest {
        // Given (Dado) - A preparação do cenário
        val dummyFileId = "123"
        val tempoAtual = System.currentTimeMillis()
        // Simulamos um timestamp de 1 hora atrás (que é menor que o nosso intervalo de 1 dia)
        val timestampRecente = tempoAtual - TimeUnit.HOURS.toMillis(1)
        
        // Criamos dados falsos para o cache (agora como Entidades)
        val mockEntity = PaliativeCareItemEntity(
            1, "Cache Teste", "Sub Cache",
            "Desc", listOf("Prof"), "Baixa", "Ref Cache"
        )
        val listaDeEntidadesDoCache = listOf(mockEntity)

        // Configura o comportamento dos mocks para ESTE cenário
        coEvery { localDataSource.hasCachedData() } returns true
        every { sharedPreferences.getLong(any(), any()) } returns timestampRecente
        coEvery { localDataSource.getPalliativeCareItems() } returns listaDeEntidadesDoCache

        // When (Quando) - A execução da ação
        val result = useCase(fileId = dummyFileId)

        // Then (Então) - A verificação do resultado e comportamentos
        assertTrue(result.isSuccess)
        val groupedData = result.getOrNull()
        assertEquals(1, groupedData?.size)
        assertEquals("Cache Teste", groupedData?.first()?.nomeCategoriaPrincipal)

        // Verificação MAIS IMPORTANTE deste teste:
        // Garantir que rede NÃO foi chamada.
        coVerify(exactly = 0) { remoteDataSource.fetchPalliativeCareData(any())}

        // Também podemos verificar que os métodos de salvar no cache não foram chamados
        coVerify(exactly = 0) { localDataSource.savePalliativeCareItems(any()) }
        coVerify(exactly = 0) { localDataSource.clearAllPalliativeCareItems() }
    }
}