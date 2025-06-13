package com.jasson.conforta

import app.cash.turbine.test
import com.jasson.conforta.domain.model.CuidadoPaliativoAgrupado
import com.jasson.conforta.data.usecase.GetPalliativeCareDataUseCase
import com.jasson.conforta.ui.screen.mainscreen.ViewModel
import com.jasson.conforta.util.MainDispatcherRule
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test


class ViewModelTest {

    // 1. Aplica a regra do Dispatcher
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    // 2. Mock da nossa única dependência: o UseCase
    private lateinit var getPalliativeCareDataUseCase: GetPalliativeCareDataUseCase

    // 3. A classe que estamos testando (System Under Test - SUT)
    private lateinit var viewModel: ViewModel

    @Before
    fun setup() {
        // Inicializa o mock antes de cada teste
        getPalliativeCareDataUseCase = mockk()
    }

    @Test
    fun `quando use case retorne sucesso, uiState deve conter dados e isLoading deve ser falso`() =
        runTest {
            // Given (Dado) - A preparação do cenário
            val mockData = listOf(
                CuidadoPaliativoAgrupado("Categoria Sucesso", emptyList())
            )
            // Configuramos o mock do UseCase para retornar sucesso com nossos dados falsos
            coEvery { getPalliativeCareDataUseCase(any(), any()) } returns Result.success(mockData)

            // When (Quando) - A ação que aciona a lógica
            // A lògica de carregar os dados é chamada no 'init' do ViewModel,
            // então a própria criação do ViewModel é a nossa ação.
            viewModel = ViewModel(getPalliativeCareDataUseCase)

            // Then (Então) - A verificação dos estados emitidos pelo StateFlow
            viewModel.uiState.test {
                // A biblioteca Turbine nos permite "esperar" por cada emissão de estado.

                // 1. Estado inicial (definido no MainUiState)
                var currentState = awaitItem()
                assertTrue(currentState.cuidadosAgrupados.isEmpty())
                assertFalse(currentState.isLoading)

                // 2. Estado de sucesso (quando loadPalliativeCareData começa)
                currentState = awaitItem()
                assertTrue(currentState.isLoading)

                // 3. Estado de carregamento (quando loadPalliativeCareData começa)
                currentState = awaitItem()
                assertFalse(currentState.isLoading)
                assertEquals(mockData, currentState.cuidadosAgrupados)
                assertTrue(currentState.errorMessages.isEmpty())

                // Garante que não há mais emissões
                cancelAndIgnoreRemainingEvents()
            }
        }
}



















