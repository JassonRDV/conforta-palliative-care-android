package com.jasson.conforta

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jasson.conforta.data.remote.CuidadoPaliativoAgrupado
import com.jasson.conforta.data.remote.DetalheSubcategoria
import com.jasson.conforta.data.usecase.GetPalliativeCareDataUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ViewModel @Inject constructor(
    private val getPalliativeCareDataUseCase: GetPalliativeCareDataUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(PalliativeCareUiState())
    val uiState: StateFlow<PalliativeCareUiState> = _uiState.asStateFlow()

    init {
        loadPalliativeCareData()
    }

    fun loadPalliativeCareData(fileId: String = DEFAULT_PALLIATIVE_CARE_FILE_ID) {
        viewModelScope.launch {
            _uiState.update { currentState ->
                currentState.copy(isLoading = true, errorMessages = emptyList())
            }

            val result = getPalliativeCareDataUseCase(fileId = fileId)

            result.fold(
                onSuccess = { dadosAgrupados ->
                    _uiState.update { currentState ->
                        currentState.copy(
                            cuidadosAgrupados = dadosAgrupados,
                            isLoading = false
                        )
                    }
                },
                onFailure = { exception ->
                    _uiState.update { currentState ->
                        currentState.copy(
                            isLoading = false,
                            errorMessages = listOf(
                                exception.message
                                    ?: "Ocorreu um erro desconhecido ao carregar dados"
                            )
                        )
                    }
                }
            )
        }
    }

    fun loadSubGridItems(subItemIndex: Int) {
        viewModelScope.launch {
            _uiState.update { currentState ->
                currentState.copy(isLoading = true, errorMessages = emptyList())
            }
            val currentCuidadosAgrupados = _uiState.value.cuidadosAgrupados
            if (subItemIndex >= 0 && subItemIndex < currentCuidadosAgrupados.size) {
                val itemAgrupadoSelecionado = currentCuidadosAgrupados[subItemIndex]
                _uiState.update { currentState ->
                    currentState.copy(
                        subGridItems = itemAgrupadoSelecionado.subcategorias,
                        isLoading = false
                    )
                }
            } else {
                _uiState.update { currentState ->
                    currentState.copy(
                        isLoading = false,
                        subGridItems = emptyList(),
                        errorMessages = listOf("Índice para subitens ($subItemIndex) é inválido.")
                    )
                }
            }
        }
    }

    fun loadDetail(detailIndex: Int = 0) {
        viewModelScope.launch {
            _uiState.update { currentState ->
                currentState.copy(
                    isLoading = true,
                    errorMessages = emptyList(),
                    detail = null
                )
            }
            val currentSubGridItems = _uiState.value.subGridItems
            if (detailIndex >= 0 && detailIndex < currentSubGridItems.size) {
                val selectedDetailItem = currentSubGridItems[detailIndex]
                _uiState.update { currentState ->
                    currentState.copy(
                        detail = selectedDetailItem,
                        isLoading = false
                    )
                }
            } else {
                _uiState.update { currentState ->
                    currentState.copy(
                        isLoading = false,
                        errorMessages = listOf(
                            "Não foi possível carregar o detalhe: índice $detailIndex é inválido."
                        )
                    )
                }
            }
        }
    }

    companion object {
        private const val DEFAULT_PALLIATIVE_CARE_FILE_ID = "1PpZVjF-myi0hPIguG_LBg9_G5_X1bg15"
    }
}

data class PalliativeCareUiState(
    val cuidadosAgrupados: List<CuidadoPaliativoAgrupado> = emptyList(),
    val subGridItems: List<DetalheSubcategoria> = emptyList(),
    val detail: DetalheSubcategoria? = null,
    val isLoading: Boolean = false,
    val errorMessages: List<String> = emptyList()
)